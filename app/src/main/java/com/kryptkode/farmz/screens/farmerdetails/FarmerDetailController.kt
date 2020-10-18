package com.kryptkode.farmz.screens.farmerdetails

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import com.kryptkode.farmz.R
import com.kryptkode.farmz.app.logger.Logger
import com.kryptkode.farmz.app.utils.StringResource
import com.kryptkode.farmz.app.utils.ToastHelper
import com.kryptkode.farmz.app.utils.livedata.extension.observe
import com.kryptkode.farmz.app.utils.livedata.extension.observeEvent
import com.kryptkode.farmz.datareturn.ScreenDataReturnBuffer
import com.kryptkode.farmz.navigation.home.HomeNavigator
import com.kryptkode.farmz.screens.farmerdetails.view.FarmerDetailView
import com.kryptkode.farmz.screens.farmers.model.FarmerView
import javax.inject.Inject

class FarmerDetailController @Inject constructor(
    private val screenDataReturnBuffer: ScreenDataReturnBuffer,
    private val homeNavigator: HomeNavigator,
    private val toastHelper: ToastHelper,
    private val stringResource: StringResource,
    private val logger: Logger
) : LifecycleObserver, FarmerDetailView.Listener {

    private lateinit var farmerId: String
    private lateinit var viewModel: FarmerDetailViewModel
    private lateinit var lifeCycleOwner: LifecycleOwner
    private lateinit var farmerListView: FarmerDetailView

    private var farmerView: FarmerView? = null

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
        checkIfPassportWasUpdated()
    }

    private fun checkIfPassportWasUpdated() {
        val key = getPhotoReturnKey()
        if (screenDataReturnBuffer.hasDataForToken(key)) {
            val newPhotoUri = screenDataReturnBuffer.getValue<String>(key)
            logger.d("New photo: $newPhotoUri")
            if (newPhotoUri != null) {
                farmerView?.let {
                    farmerView = it.copy(passportPhoto = newPhotoUri)
                    viewModel.updatePassport(farmerView!!)
                    farmerListView.updateFarmerPhoto(newPhotoUri)
                    screenDataReturnBuffer.removeValue(key)
                    logger.d("updating photo...")
                }
            } else {
                toastHelper.showMessage(stringResource.getString(R.string.update_image_error_msg))
            }
        }
    }

    private fun setupObservers() {
        @Suppress("COMPATIBILITY_WARNING")
        viewModel.farmer.observe(lifeCycleOwner) {
            farmerView = it
            farmerListView.bindFarmer(it)
        }

        viewModel.showErrorMessage.observeEvent(lifeCycleOwner) {
            toastHelper.showMessage("Error occurred while updating photo")
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
        farmerView?.let {
            homeNavigator.toImageViewer(it.passportPhoto, getPhotoReturnKey())
        }
    }

    override fun onClickBack() {
        homeNavigator.navigateUp()
    }

    override fun onCaptureFarm() {
        homeNavigator.toCaptureFarm(farmerId)
    }

    private fun getPhotoReturnKey(): String {
        return javaClass.name.plus(PHOTO_RETURN_KEY)
    }

    companion object {
        private const val PHOTO_RETURN_KEY = "PHOTO_RETURN_KEY"
    }
}