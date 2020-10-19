package com.kryptkode.farmz.screens.dashboard

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.kryptkode.farmz.app.logger.Logger
import com.kryptkode.farmz.app.utils.ToastHelper
import com.kryptkode.farmz.navigation.home.HomeNavigator
import com.kryptkode.farmz.screens.common.ViewFactory
import com.kryptkode.farmz.screens.common.fragment.BaseFragment
import com.kryptkode.farmz.screens.dashboard.view.DashboardViewMvc
import com.kryptkode.farmz.screens.farm.model.UiFarm
import javax.inject.Inject

class DashboardFragment : BaseFragment(), DashboardViewMvc.Listener {
    @Inject
    lateinit var logger: Logger

    @Inject
    lateinit var toastHelper: ToastHelper

    @Inject
    lateinit var homeNavigator: HomeNavigator

    @Inject
    lateinit var viewFactory: ViewFactory

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: DashboardViewModel by viewModels { viewModelFactory }

    private lateinit var viewMvc: DashboardViewMvc

    override fun onAttach(context: Context) {
        super.onAttach(context)
        controllerComponent.inject(this)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewMvc = viewFactory.getDashboardView(container)
        setupObservers()
        return viewMvc.rootView
    }

    private fun setupObservers() {

    }

    override fun onStart() {
        super.onStart()
        viewMvc.registerListener(this)
    }

    override fun onStop() {
        super.onStop()
        viewMvc.unregisterListener(this)
    }

    override fun onViewAllFarmsClick() {
        homeNavigator.toFarmsList()
    }

    override fun onItemClick(item: UiFarm) {
        homeNavigator.toFarmDetails(item.id)
    }

    override fun onLoadError(s: String) {
        toastHelper.showMessage(s)
    }
}