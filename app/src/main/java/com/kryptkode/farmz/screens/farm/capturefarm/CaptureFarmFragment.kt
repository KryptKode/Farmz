package com.kryptkode.farmz.screens.farm.capturefarm

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
import com.kryptkode.farmz.app.utils.livedata.extension.observeEvent
import com.kryptkode.farmz.datareturn.ScreenDataReturnBuffer
import com.kryptkode.farmz.navigation.home.HomeNavigator
import com.kryptkode.farmz.screens.common.ViewFactory
import com.kryptkode.farmz.screens.common.fragment.BaseFragment
import com.kryptkode.farmz.screens.farm.FarmValidator
import com.kryptkode.farmz.screens.farm.FarmViewModel
import com.kryptkode.farmz.screens.farm.capturefarm.view.CaptureFarmView
import com.kryptkode.farmz.screens.farm.model.UiFarm
import com.kryptkode.farmz.screens.farm.model.UiFarmLocation
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
    lateinit var validator: FarmValidator

    @Inject
    lateinit var screenDataReturnBuffer: ScreenDataReturnBuffer

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: FarmViewModel by viewModels { viewModelFactory }

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
        viewMvc.getMap().onCreate(savedInstanceState)
        viewMvc.onSelectLocation(viewModel.getLocation())
        return viewMvc.rootView
    }

    override fun onStart() {
        super.onStart()
        viewMvc.getMap().onStart()
        viewMvc.registerListener(this)
        checkIfRegionWasSelected()
    }

    override fun onResume() {
        super.onResume()
        viewMvc.getMap().onResume()
    }

    override fun onPause() {
        super.onPause()
        viewMvc.getMap().onPause()
    }

    override fun onStop() {
        super.onStop()
        viewMvc.unregisterListener(this)
        viewMvc.getMap().onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewMvc.getMap().onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        viewMvc.getMap().onSaveInstanceState(outState)
    }


    private fun checkIfRegionWasSelected() {
        val key = getRegionReturnKey()
        if (screenDataReturnBuffer.hasDataForToken(key)) {
            val coordinates = screenDataReturnBuffer.getValue<List<UiFarmLocation>>(key)
            logger.d("New locations: $coordinates")
            if (coordinates != null) {
                viewModel.setLocation(coordinates)
                viewMvc.onSelectLocation(coordinates)
                screenDataReturnBuffer.removeValue(key)
                logger.d("updating coordinates...")
            } else {
                toastHelper.showMessage(getString(R.string.add_location_error_msg))
            }
        }
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
    }

    override fun onAddLocation() {
        homeNavigator.toSelectLocation(viewModel.getLocation(), getRegionReturnKey())
    }

    override fun onSave(farmName: String, farmLocation: String) {
        viewMvc.clearErrors()
        val uiFarm = UiFarm(0, args.farmerId, farmName, farmLocation, viewModel.getLocation())
        if (validator.validateFarm(uiFarm)) {
            viewModel.addFarm(uiFarm)
        }
    }

    override fun onBackClick() {
        homeNavigator.navigateUp()
    }

    private fun getRegionReturnKey(): String {
        return javaClass.name.plus(REGION_RETURN_KEY)
    }

    companion object {

        private const val REGION_RETURN_KEY = "REGION_RETURN_KEY"
    }
}