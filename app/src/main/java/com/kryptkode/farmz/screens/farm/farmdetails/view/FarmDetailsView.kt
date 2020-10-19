package com.kryptkode.farmz.screens.farm.farmdetails.view

import com.google.android.gms.maps.MapView
import com.kryptkode.farmz.screens.common.view.BaseObservableViewMvc
import com.kryptkode.farmz.screens.farm.model.UiFarm
import com.kryptkode.farmz.screens.farm.model.UiFarmLocation

abstract class FarmDetailsView : BaseObservableViewMvc<FarmDetailsView.Listener>() {

    interface Listener {
        fun onAddLocation()
        fun onSave(farmName:String, farmLocation:String)
        fun onBackClick()
    }

    abstract fun showLocationError(message: String)
    abstract fun showNameError(message: String)

    abstract fun clearErrors()

    abstract fun getMap():MapView

    abstract fun showLoading()
    abstract fun hideLoading()
    abstract fun onSelectLocation(locations:List<UiFarmLocation>)

    abstract fun bindFarm(uiFarm: UiFarm)

}