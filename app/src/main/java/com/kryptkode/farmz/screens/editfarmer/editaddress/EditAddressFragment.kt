package com.kryptkode.farmz.screens.editfarmer.editaddress

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.kryptkode.farmz.app.logger.Logger
import com.kryptkode.farmz.app.utils.ToastHelper
import com.kryptkode.farmz.app.utils.livedata.extension.observeEvent
import com.kryptkode.farmz.navigation.home.HomeNavigator
import com.kryptkode.farmz.screens.common.ViewFactory
import com.kryptkode.farmz.screens.common.fragment.BaseFragment
import com.kryptkode.farmz.screens.editfarmer.EditFarmerViewModel
import com.kryptkode.farmz.screens.editfarmer.editaddress.view.EditAddressView
import com.kryptkode.farmz.screens.farmers.model.FarmerView
import javax.inject.Inject

class EditAddressFragment : BaseFragment(), EditAddressView.Listener {

    @Inject
    lateinit var logger: Logger

    @Inject
    lateinit var toastHelper: ToastHelper

    @Inject
    lateinit var homeNavigator: HomeNavigator

    @Inject
    lateinit var viewFactory: ViewFactory

    @Inject
    lateinit var validator: AddressValidator


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: EditFarmerViewModel by viewModels { viewModelFactory }

    private val args by navArgs<EditAddressFragmentArgs>()

    private lateinit var viewMvc: EditAddressView

    override fun onAttach(context: Context) {
        super.onAttach(context)
        controllerComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewMvc = viewFactory.getEditAddressView(container)
        setupObservers()
        viewModel.getFarmer(args.farmerId)
        return viewMvc.rootView
    }

    override fun onStart() {
        super.onStart()
        viewMvc.registerListener(this)
    }

    private fun setupObservers() {

        validator.addressError.observeEvent(viewLifecycleOwner) {
            viewMvc.showAddressError(it)
        }

        validator.cityError.observeEvent(viewLifecycleOwner) {
            viewMvc.showCityError(it)
        }

        validator.stateError.observeEvent(viewLifecycleOwner) {
            viewMvc.showStateError(it)
        }

        validator.lgaError.observeEvent(viewLifecycleOwner) {
            viewMvc.showLgaError(it)
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
            viewMvc.bindFarmer(it)
        }
    }

    override fun onStop() {
        super.onStop()
        viewMvc.unregisterListener(this)
    }

    override fun onSave(farmer: FarmerView) {
        viewMvc.clearErrors()
        if (validator.validateAddress(farmer)) {
            viewModel.save(farmer)
        }
    }

    override fun onBackClick() {
        homeNavigator.navigateUp()
    }

}