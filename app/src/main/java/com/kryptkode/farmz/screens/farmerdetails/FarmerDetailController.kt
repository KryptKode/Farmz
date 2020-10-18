package com.kryptkode.farmz.screens.farmerdetails

import androidx.lifecycle.*
import com.kryptkode.farmz.navigation.home.HomeNavigator
import com.kryptkode.farmz.screens.farmerdetails.view.FarmerDetailView
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class FarmerDetailController @Inject constructor(
    private val homeNavigator: HomeNavigator
) : LifecycleObserver, FarmerDetailView.Listener {

    private lateinit var farmerId: String
    private lateinit var viewModel: FarmerDetailViewModel
    private lateinit var lifeCycleOwner: LifecycleOwner
    private lateinit var farmerListView: FarmerDetailView

    fun bindView(farmerListView: FarmerDetailView) {
        this.farmerListView = farmerListView
    }

    fun bindLifeCycleOwner(lifecycleOwner: LifecycleOwner) {
        this.lifeCycleOwner = lifecycleOwner
    }

    fun bindViewModel(viewModel: FarmerDetailViewModel) {
        this.viewModel = viewModel
    }


    fun bindFarmerId(farmerId: String) {
        this.farmerId = farmerId
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        farmerListView.registerListener(this)
        lifeCycleOwner.lifecycleScope.launch {
            viewModel.getFarmer(farmerId).collect {
                farmerListView.bindFarmer(it)
            }
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        farmerListView.unregisterListener(this)
    }

    override fun onEditPersonalDetails() {
        homeNavigator.toEditPersonalDetails(farmerId)
    }

    override fun onEditContactDetails() {
        homeNavigator.toEditContactDetails(farmerId)
    }

    override fun onEditIdentification() {
        homeNavigator.toEditIdentification(farmerId)
    }

    override fun onEditAddress() {
        homeNavigator.toEditAddress(farmerId)
    }

    override fun onClickPic() {
        homeNavigator.toPic(farmerId)
    }

    override fun onClickBack() {
        homeNavigator.navigateUp()
    }

    override fun onCaptureFarm() {
        homeNavigator.toCaptureFarm(farmerId)
    }
}