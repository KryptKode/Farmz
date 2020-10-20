package com.kryptkode.farmz.screens.farm.farmlist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.kryptkode.farmz.app.utils.ToastHelper
import com.kryptkode.farmz.navigation.home.HomeNavigator
import com.kryptkode.farmz.screens.common.ViewFactory
import com.kryptkode.farmz.screens.common.fragment.BaseFragment
import com.kryptkode.farmz.screens.farm.FarmViewModel
import com.kryptkode.farmz.screens.farm.farmlist.view.FarmListViewMvc
import com.kryptkode.farmz.screens.farm.model.UiFarm
import kotlinx.coroutines.launch
import javax.inject.Inject

class FarmListFragment : BaseFragment(), FarmListViewMvc.Listener {

    @Inject
    lateinit var viewFactory: ViewFactory

    @Inject
    lateinit var toastHelper: ToastHelper

    @Inject
    lateinit var homeNavigator: HomeNavigator

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: FarmViewModel by viewModels { viewModelFactory }

    private lateinit var viewMvc: FarmListViewMvc

    override fun onAttach(context: Context) {
        super.onAttach(context)
        controllerComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewMvc = viewFactory.getFarmListView(container)
        setupObservers()
        return viewMvc.rootView
    }

    private fun setupObservers() {
        viewModel.farms.observe(viewLifecycleOwner){
            viewLifecycleOwner.lifecycleScope.launch {
                viewMvc.bindFarms(it)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        viewMvc.registerListener(this)
    }

    override fun onStop() {
        super.onStop()
        viewMvc.unregisterListener(this)
    }

    override fun onItemClick(item: UiFarm) {
        homeNavigator.toFarmDetails(item.id)
    }

    override fun onLoadError(error: String) {
        toastHelper.showMessage(error)
    }


}