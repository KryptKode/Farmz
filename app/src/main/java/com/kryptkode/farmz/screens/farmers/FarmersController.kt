package com.kryptkode.farmz.screens.farmers

import androidx.lifecycle.*
import com.kryptkode.farmz.app.utils.ToastHelper
import com.kryptkode.farmz.navigation.home.HomeNavigator
import com.kryptkode.farmz.screens.farmers.model.FarmerView
import com.kryptkode.farmz.screens.farmers.view.FarmerListView
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class FarmersController @Inject constructor(
    private val homeNavigator: HomeNavigator,
    private val toastHelper: ToastHelper
) : LifecycleObserver, FarmerListView.Listener {

    private lateinit var viewModel: FarmersViewModel
    private lateinit var lifeCycleOwner: LifecycleOwner
    private lateinit var farmerListView: FarmerListView

    fun bindView(farmerListView: FarmerListView) {
        this.farmerListView = farmerListView
    }

    fun bindLifeCycleOwner(lifecycleOwner: LifecycleOwner) {
        this.lifeCycleOwner = lifecycleOwner
    }

    fun bindViewModel(viewModel: FarmersViewModel) {
        this.viewModel = viewModel
    }

    fun getData(){
        lifeCycleOwner.lifecycleScope.launch {
            viewModel.loadFarmers().collectLatest {
                farmerListView.bindFarmers(it)
            }
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        farmerListView.registerListener(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        farmerListView.unregisterListener(this)
    }

    override fun onItemClick(item: FarmerView) {
        homeNavigator.farmersListToFarmersDetail(item.id)
    }

    override fun onLoadError(message: String) {
        toastHelper.showMessage(message)
    }

}