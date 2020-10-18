package com.kryptkode.farmz.screens.editfarmer.editcontactdetails

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
import com.kryptkode.farmz.screens.editfarmer.editaddress.EditAddressFragmentArgs
import com.kryptkode.farmz.screens.editfarmer.editcontactdetails.view.EditContactDetailsView
import com.kryptkode.farmz.screens.farmers.model.FarmerView
import javax.inject.Inject

class EditContactDetailsFragment : BaseFragment(), EditContactDetailsView.Listener {

    @Inject
    lateinit var logger: Logger

    @Inject
    lateinit var toastHelper: ToastHelper

    @Inject
    lateinit var homeNavigator: HomeNavigator

    @Inject
    lateinit var viewFactory: ViewFactory

    @Inject
    lateinit var validator: ContactDetailsValidator


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: EditFarmerViewModel by viewModels { viewModelFactory }

    private val args by navArgs<EditAddressFragmentArgs>()

    private lateinit var viewMvc: EditContactDetailsView

    override fun onAttach(context: Context) {
        super.onAttach(context)
        controllerComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewMvc = viewFactory.getEditContactView(container)
        setupObservers()
        viewModel.getFarmer(args.farmerId)
        return viewMvc.rootView
    }

    override fun onStart() {
        super.onStart()
        viewMvc.registerListener(this)
    }

    private fun setupObservers() {

        validator.emailAddressError.observeEvent(viewLifecycleOwner) {
            viewMvc.showEmailError(it)
        }

        validator.mobileNumberError.observeEvent(viewLifecycleOwner) {
            viewMvc.showMobileError(it)
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
        if (validator.validateContact(farmer)) {
            viewModel.save(farmer)
        }
    }

    override fun onBackClick() {
        homeNavigator.navigateUp()
    }
}