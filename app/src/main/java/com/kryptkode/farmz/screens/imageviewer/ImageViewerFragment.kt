package com.kryptkode.farmz.screens.imageviewer


import android.content.Context
import android.os.Bundle
import android.view.View
import com.kryptkode.farmz.R
import com.kryptkode.farmz.app.utils.extension.beGoneIf
import com.kryptkode.farmz.app.utils.viewbinding.viewBinding
import com.kryptkode.farmz.databinding.FragmentImageViewBinding
import com.kryptkode.farmz.screens.common.fragment.BaseFragment
import javax.inject.Inject


class ImageViewerFragment : BaseFragment(R.layout.fragment_image_view) {

    private var fullScreen: Boolean = false

    @Inject
    lateinit var systemUIHelper: SystemUIHelper

    private val binding by viewBinding(FragmentImageViewBinding::bind)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        controllerComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fullScreen = savedInstanceState?.getBoolean(FULL_SCREEN_KEY) ?: false
        changeSystemUI()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(FULL_SCREEN_KEY, fullScreen)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.image.setOnClickListener {
            toggleFullScreen()
        }

        binding.imgEdit.setOnClickListener {

        }
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

    companion object {
        private const val FULL_SCREEN_KEY = "full_screen"
    }
}