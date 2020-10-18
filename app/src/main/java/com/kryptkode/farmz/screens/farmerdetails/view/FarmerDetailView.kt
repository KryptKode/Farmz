package com.kryptkode.farmz.screens.farmerdetails.view

import com.kryptkode.farmz.screens.common.view.BaseObservableViewMvc
import com.kryptkode.farmz.screens.farmers.model.FarmerView

abstract class FarmerDetailView : BaseObservableViewMvc<FarmerDetailView.Listener>() {
    interface Listener {
        fun onEditPersonalDetails()
        fun onEditContactDetails()
        fun onEditIdentification()
        fun onEditAddress()
        fun onClickPic()
        fun onClickBack()
    }

    abstract fun bindFarmer(farmer: FarmerView)
}