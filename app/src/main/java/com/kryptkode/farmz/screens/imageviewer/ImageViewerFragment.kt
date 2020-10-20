package com.kryptkode.farmz.screens.imageviewer


import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.navigation.fragment.navArgs
import com.kryptkode.farmz.R
import com.kryptkode.farmz.app.logger.Logger
import com.kryptkode.farmz.app.utils.ToastHelper
import com.kryptkode.farmz.app.utils.extension.beGone
import com.kryptkode.farmz.app.utils.extension.beGoneIf
import com.kryptkode.farmz.app.utils.extension.beVisible
import com.kryptkode.farmz.app.utils.extension.openAppSettings
import com.kryptkode.farmz.app.utils.file.FileUtils
import com.kryptkode.farmz.app.utils.viewbinding.viewBinding
import com.kryptkode.farmz.databinding.FragmentImageViewBinding
import com.kryptkode.farmz.datareturn.ScreenDataReturnBuffer
import com.kryptkode.farmz.navigation.home.HomeNavigator
import com.kryptkode.farmz.screens.common.dialog.DialogEventBus
import com.kryptkode.farmz.screens.common.fragment.BaseFragment
import com.kryptkode.farmz.screens.common.imageloader.ImageLoader
import com.kryptkode.farmz.screens.infodialog.InfoEvent
import com.kryptkode.flashalerts.screens.common.infodialog.InfoDialogBuilder
import timber.log.Timber
import java.io.File
import java.io.IOException
import javax.inject.Inject

class ImageViewerFragment : BaseFragment(R.layout.fragment_image_view), DialogEventBus.Listener {

    private var fullScreen: Boolean = false

    private var currentPhotoPath: String? = null

    @Inject
    lateinit var systemUIHelper: SystemUIHelper

    @Inject
    lateinit var imageLoader: ImageLoader

    @Inject
    lateinit var toastHelper: ToastHelper

    @Inject
    lateinit var fileUtils: FileUtils

    @Inject
    lateinit var homeNavigator: HomeNavigator

    @Inject
    lateinit var dialogEventBus: DialogEventBus

    @Inject
    lateinit var screenDataReturnBuffer: ScreenDataReturnBuffer

    @Inject
    lateinit var logger: Logger

    private val binding by viewBinding(FragmentImageViewBinding::bind)

    private val args by navArgs<ImageViewerFragmentArgs>()

    private val takePicture = registerForActivityResult(ActivityResultContracts.TakePicture()) {
        logger.d("Picture taken? $it --- path: $currentPhotoPath")
        if (it) {
            loadImage(currentPhotoPath ?: "")
            compressFile()
        }else {
            toastHelper.showMessage(getString(R.string.take_pic_error_msg))
        }
    }

    private fun compressFile() {
        showLoading()
        var compressedFile = fileUtils.compressFile(File(currentPhotoPath ?: ""))
        if (compressedFile != null) {
            //delete the uncompressed file
            fileUtils.deleteFile(File(currentPhotoPath ?: return))
        }else {
            compressedFile = File(currentPhotoPath ?: "")
        }

        hideLoading()
        val fileUrl = fileUtils.getFileUri(compressedFile)
        logger.d("FIleURI: $fileUrl")
        //return URI
        screenDataReturnBuffer.putValue(args.returnKey, fileUrl)
        homeNavigator.navigateUp()
    }

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                openCamera()
            } else {
                if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
                    toastHelper.showMessage(getString(R.string.allow_camera_permission_msg))
                } else {
                    showCameraPermissionNeverAsk()
                }
            }
        }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        controllerComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fullScreen = savedInstanceState?.getBoolean(FULL_SCREEN_KEY) ?: false
    }

    private fun checkCameraPermission() {
        when {
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED -> {

                openCamera()
            }

            shouldShowRequestPermissionRationale(Manifest.permission.CAMERA) -> {
                showCameraPermissionRationale()
            }
            else -> {
                requestPermissionLauncher.launch(
                    Manifest.permission.CAMERA
                )
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(FULL_SCREEN_KEY, fullScreen)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        changeSystemUI()

        loadImage(args.imagePath)

        binding.image.setOnClickListener {
            toggleFullScreen()
        }

        binding.imgEdit.setOnClickListener {
            checkCameraPermission()
        }
    }

    private fun loadImage(uri:String) {
        imageLoader.load(uri, binding.image)
    }

    private fun openCamera() {
        val photoFile: File? = try {
            createImageFile()
        } catch (ex: Exception) {
            // Error occurred while creating the File
            Timber.e(ex)
            toastHelper.showMessage(getString(R.string.change_pic_create_file_error))
            null
        }

        photoFile?.let {
            val outputUri = FileProvider.getUriForFile(
                requireContext(),
                "${requireContext().packageName}.provider",
                photoFile
            )
            takePicture.launch(outputUri)
        }
    }


    @Throws(IOException::class)
    private fun createImageFile(): File {
        return fileUtils.createImageFile().apply {
            // Save a file: path for use with ACTION_VIEW intents
            Timber.d("Created image file with path: $absolutePath")
            currentPhotoPath = absolutePath
        }
    }


    private fun getRootPathCompressed(): String {
        return fileUtils.getRootPathCompressed()
    }

    private fun changeSystemUI() {
        binding.imgEdit.beGoneIf(fullScreen)
        if (fullScreen) {
            systemUIHelper.hideSystemUI()
        } else {
            systemUIHelper.showSystemUi()
        }
    }

    private fun toggleFullScreen() {
        fullScreen = !fullScreen
        changeSystemUI()
    }

    override fun onStart() {
        super.onStart()
        dialogEventBus.registerListener(this)
    }

    override fun onStop() {
        super.onStop()
        dialogEventBus.unregisterListener(this)
    }

    private fun showCameraPermissionRationale() {
        val info = InfoDialogBuilder()
            .title(getString(R.string.allow_camera_permission_title))
            .message(getString(R.string.allow_camera_permission_msg))
            .payload(PAYLOAD_RATIONALE)
            .buildInfo()
        homeNavigator.toInfoDialog(info)
    }

    private fun showCameraPermissionNeverAsk() {
        val info = InfoDialogBuilder()
            .title(getString(R.string.allow_camera_permission_title))
            .message(getString(R.string.allow_camera_permission_never_ask))
            .payload(PAYLOAD_NEVER_ASK)
            .buildInfo()
        homeNavigator.toInfoDialog(info)
    }

    private fun showLoading(){
        binding.progress.root.beVisible()
    }

    private fun hideLoading(){
        binding.progress.root.beGone()
    }

    override fun onDialogEvent(event: Any?) {
        when (event) {
            is InfoEvent -> {
                when (event.payload) {
                    PAYLOAD_NEVER_ASK -> {
                        when (event.button) {
                            InfoEvent.Button.POSITIVE -> {
                                requireContext().openAppSettings()
                            }
                            else -> {

                            }
                        }
                    }

                    PAYLOAD_RATIONALE -> {
                        when (event.button) {
                            InfoEvent.Button.POSITIVE -> {
                                requestPermissionLauncher.launch(
                                    Manifest.permission.CAMERA
                                )
                            }
                            else -> {

                            }
                        }
                    }
                }
            }
        }
    }

    companion object {
        private const val FULL_SCREEN_KEY = "full_screen"
        private const val PAYLOAD_RATIONALE = "PAYLOAD_RATIONALE"
        private const val PAYLOAD_NEVER_ASK = "PAYLOAD_NEVER_ASK"
    }
}