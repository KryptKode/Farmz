package com.kryptkode.farmz.screens.capturefarm

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
import com.kryptkode.farmz.screens.capturefarm.model.UiFarm
import com.kryptkode.farmz.screens.capturefarm.view.CaptureFarmView
import com.kryptkode.farmz.screens.common.ViewFactory
import com.kryptkode.farmz.screens.common.fragment.BaseFragment
import javax.inject.Inject

class CaptureFarmFragment : BaseFragment(), CaptureFarmView.Listener {

    @Inject
    lateinit var logger: Logger

    @Inject
    lateinit var toastHelper: ToastHelper

    @Inject
    lateinit var homeNavigator: HomeNavigator

    @Inject
    lateinit var viewFactory: ViewFactory

    @Inject
    lateinit var validator: CaptureFarmValidator


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: CaptureFarmViewModel by viewModels { viewModelFactory }

    private val args by navArgs<CaptureFarmFragmentArgs>()


    private lateinit var viewMvc: CaptureFarmView

    override fun onAttach(context: Context) {
        super.onAttach(context)
        controllerComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewMvc = viewFactory.getCaptureView(container)
        setupObservers()
        return viewMvc.rootView
    }

    override fun onStart() {
        super.onStart()
        viewMvc.registerListener(this)
    }

    override fun onStop() {
        super.onStop()
        viewMvc.unregisterListener(this)
    }

    private fun setupObservers() {
        validator.farmNameError.observeEvent(viewLifecycleOwner) {
            viewMvc.showNameError(it)
        }

        validator.farmCoordinatesError.observeEvent(viewLifecycleOwner) {
            toastHelper.showMessage(it)
        }

        validator.farmLocationError.observeEvent(viewLifecycleOwner) {
            viewMvc.showLocationError(it)
        }
    }

    override fun onAddLocation() {

    }

    override fun onSave(farmName: String, farmLocation: String) {
        viewMvc.clearErrors()
        val uiFarm = UiFarm(0, args.farmerId, farmName, farmLocation, viewModel.farmLocation)
        if (validator.validateFarm(uiFarm)) {
            viewModel.saveFarm(uiFarm)
        }
    }

    override fun onBackClick() {
        homeNavigator.navigateUp()
    }
}