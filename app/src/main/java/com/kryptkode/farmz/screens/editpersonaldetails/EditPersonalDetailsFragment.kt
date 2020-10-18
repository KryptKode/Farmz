package com.kryptkode.farmz.screens.editpersonaldetails

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.kryptkode.farmz.app.utils.ToastHelper
import com.kryptkode.farmz.app.utils.livedata.extension.observeEvent
import com.kryptkode.farmz.navigation.home.HomeNavigator
import com.kryptkode.farmz.screens.common.ViewFactory
import com.kryptkode.farmz.screens.common.fragment.BaseFragment
import com.kryptkode.farmz.screens.editpersonaldetails.view.EditPersonalDetailsView
import com.kryptkode.farmz.screens.farmers.model.FarmerView
import javax.inject.Inject

class EditPersonalDetailsFragment : BaseFragment(), EditPersonalDetailsView.Listener {

    @Inject
    lateinit var toastHelper: ToastHelper

    @Inject
    lateinit var homeNavigator: HomeNavigator

    @Inject
    lateinit var viewFactory: ViewFactory

    @Inject
    lateinit var validator: PersonalDetailsValidator

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: EditPersonalDetailsViewModel by viewModels { viewModelFactory }

    private val args by navArgs<EditPersonalDetailsFragmentArgs>()

    private lateinit var viewMvc: EditPersonalDetailsView

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
        setupObservers()
        viewModel.getFarmer(args.farmerId)
        return viewMvc.rootView
    }

    override fun onStart() {
        super.onStart()
        viewMvc.registerListener(this)
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
            viewMvc.bindPersonalDetails(it)
        }
    }

    override fun onStop() {
        super.onStop()
        viewMvc.unregisterListener(this)
    }

    override fun onSave(farmer: FarmerView) {
        if (validator.validatePersonalDetails(farmer)) {
            viewModel.save(farmer)
        }
    }

    override fun onBackClick() {
        homeNavigator.navigateUp()
    }

    override fun onChangePic() {
        homeNavigator.toUpdatePhoto()
    }

}