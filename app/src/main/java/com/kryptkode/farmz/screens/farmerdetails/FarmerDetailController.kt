package com.kryptkode.farmz.screens.farmerdetails

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import com.kryptkode.farmz.app.utils.livedata.extension.observe
import com.kryptkode.farmz.navigation.home.HomeNavigator
import com.kryptkode.farmz.screens.farmerdetails.view.FarmerDetailView
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
        setupObservers()
        viewModel.getFarmer(farmerId)
    }

    private fun setupObservers() {
        @Suppress("COMPATIBILITY_WARNING")
        viewModel.farmer.observe(lifeCycleOwner){
            farmerListView.bindFarmer(it)
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
        homeNavigator.toImageViewer("", "")
    }

    override fun onClickBack() {
        homeNavigator.navigateUp()
    }

    override fun onCaptureFarm() {
        homeNavigator.toCaptureFarm(farmerId)
    }
}