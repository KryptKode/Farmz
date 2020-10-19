package com.kryptkode.farmz.screens.farmerdetails.view

import androidx.paging.PagingData
import com.kryptkode.farmz.screens.common.view.BaseObservableViewMvc
import com.kryptkode.farmz.screens.farm.model.UiFarm
import com.kryptkode.farmz.screens.farmers.model.FarmerView

abstract class FarmerDetailView : BaseObservableViewMvc<FarmerDetailView.Listener>() {
    interface Listener {
        fun onEditPersonalDetails()
        fun onEditContactDetails()
        fun onEditIdentification()
        fun onEditAddress()
        fun onClickPic()
        fun onClickBack()
        fun onCaptureFarm()
        fun onItemClick(item: UiFarm)
        fun onLoadError(error: String)
    }

    abstract fun bindFarmer(farmer: FarmerView)
    abstract fun updateFarmerPhoto(newPhotoUri: String)
    abstract suspend fun bindFarms(data: PagingData<UiFarm>)
}