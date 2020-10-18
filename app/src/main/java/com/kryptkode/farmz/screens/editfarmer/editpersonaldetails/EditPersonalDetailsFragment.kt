package com.kryptkode.farmz.screens.editfarmer.editpersonaldetails

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.kryptkode.farmz.R
import com.kryptkode.farmz.app.logger.Logger
import com.kryptkode.farmz.app.utils.ToastHelper
import com.kryptkode.farmz.app.utils.date.DisplayedDateFormatter
import com.kryptkode.farmz.app.utils.livedata.extension.observeEvent
import com.kryptkode.farmz.datareturn.ScreenDataReturnBuffer
import com.kryptkode.farmz.navigation.home.HomeNavigator
import com.kryptkode.farmz.screens.common.ViewFactory
import com.kryptkode.farmz.screens.common.dialog.DialogEventBus
import com.kryptkode.farmz.screens.common.fragment.BaseFragment
import com.kryptkode.farmz.screens.datedialog.DateSelectedEvent
import com.kryptkode.farmz.screens.editfarmer.EditFarmerViewModel
import com.kryptkode.farmz.screens.editfarmer.editpersonaldetails.view.EditPersonalDetailsView
import com.kryptkode.farmz.screens.farmers.model.FarmerView
import javax.inject.Inject

class EditPersonalDetailsFragment : BaseFragment(), EditPersonalDetailsView.Listener,
    DialogEventBus.Listener {

    @Inject
    lateinit var logger: Logger

    @Inject
    lateinit var toastHelper: ToastHelper

    @Inject
    lateinit var homeNavigator: HomeNavigator

    @Inject
    lateinit var viewFactory: ViewFactory

    @Inject
    lateinit var validator: PersonalDetailsValidator

    @Inject
    lateinit var dialogEventBus: DialogEventBus

    @Inject
    lateinit var displayedDateFormatter: DisplayedDateFormatter

    @Inject
    lateinit var screenDataReturnBuffer: ScreenDataReturnBuffer

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: EditFarmerViewModel by viewModels { viewModelFactory }

    private val args by navArgs<EditPersonalDetailsFragmentArgs>()

    private lateinit var viewMvc: EditPersonalDetailsView

    private var farmerView: FarmerView? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        controllerComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewMvc = viewFactory.getEditPersonalDetailsView(container)
        initMaritalData()
        initGenderData()
        setupObservers()
        viewModel.getFarmer(args.farmerId)
        return viewMvc.rootView
    }

    private fun initGenderData() {
        val genders = resources.getStringArray(R.array.data_gender).toList()
        logger.d("Genders $genders")
        viewMvc.bindGenderItems(genders)
    }

    private fun initMaritalData() {
        val maritalStatuses = resources.getStringArray(R.array.data_marital_status).toList()
        logger.d("Marital statuses $maritalStatuses")
        viewMvc.bindMaritalStatusItems(maritalStatuses)
    }

    override fun onStart() {
        super.onStart()
        viewMvc.registerListener(this)
        dialogEventBus.registerListener(this)
        checkIfPassportWasUpdated()
    }

    private fun checkIfPassportWasUpdated() {
        val key = getPhotoReturnKey()
        if (screenDataReturnBuffer.hasDataForToken(key)) {
            val newPhotoUri = screenDataReturnBuffer.getValue<String>(key)
            logger.d("New photo: $newPhotoUri")
            if (newPhotoUri != null) {
                farmerView?.let {
                    farmerView = it.copy(passportPhoto = newPhotoUri)
                    viewModel.updatePassport(farmerView!!)
                    viewMvc.bindPic(newPhotoUri)
                    screenDataReturnBuffer.removeValue(key)
                    logger.d("updating photo...")
                }
            } else {
                toastHelper.showMessage(getString(R.string.update_image_error_msg))
            }
        }
    }

    private fun setupObservers() {

        validator.firstNameError.observeEvent(viewLifecycleOwner) {
            viewMvc.showFirstNameError(it)
        }

        validator.lastNameError.observeEvent(viewLifecycleOwner) {
            viewMvc.showLastNameError(it)
        }

        validator.dobError.observeEvent(viewLifecycleOwner) {
            viewMvc.showDobError(it)
        }

        validator.genderError.observeEvent(viewLifecycleOwner) {
            viewMvc.showGenderError(it)
        }

        validator.occupationError.observeEvent(viewLifecycleOwner) {
            viewMvc.showOccupationError(it)
        }

        validator.nationalityError.observeEvent(viewLifecycleOwner) {
            viewMvc.showNationalityError(it)
        }

        validator.maritalStatusError.observeEvent(viewLifecycleOwner) {
            viewMvc.showMaritalStatusError(it)
        }


        viewModel.showLoading.observeEvent(viewLifecycleOwner) {
            viewMvc.showLoading()
        }

        viewModel.hideLoading.observeEvent(viewLifecycleOwner) {
            viewMvc.hideLoading()
        }

        viewModel.goToNext.observeEvent(viewLifecycleOwner) {
            homeNavigator.navigateUp()
        }

        viewModel.showErrorMessage.observeEvent(viewLifecycleOwner) {
            toastHelper.showMessage(it)
        }

        viewModel.farmer.observe(viewLifecycleOwner) {
            farmerView = it
            viewMvc.bindPersonalDetails(it)
        }
    }

    override fun onStop() {
        super.onStop()
        viewMvc.unregisterListener(this)
        dialogEventBus.unregisterListener(this)
    }

    override fun onSave(farmer: FarmerView) {
        viewMvc.clearErrors()
        if (validator.validatePersonalDetails(farmer)) {
            viewModel.save(farmer)
        }
    }

    override fun onBackClick() {
        homeNavigator.navigateUp()
    }

    override fun onChangePic(photoUri: String) {
        homeNavigator.toImageViewer(photoUri, getPhotoReturnKey())
    }

    override fun onChooseDate(date: String) {
        homeNavigator.toDatePicker(displayedDateFormatter.parseDisplayedDate(date))
    }

    override fun onDialogEvent(event: Any?) {
        when (event) {
            is DateSelectedEvent -> {
                val date = event.date
                viewMvc.onDateSelected(displayedDateFormatter.formatToDisplayedDate(date))
            }
        }
    }

    private fun getPhotoReturnKey(): String {
        return javaClass.name.plus(PHOTO_RETURN_KEY)
    }

    companion object {
        private const val PHOTO_RETURN_KEY = "PHOTO_RETURN_KEY"
    }

}