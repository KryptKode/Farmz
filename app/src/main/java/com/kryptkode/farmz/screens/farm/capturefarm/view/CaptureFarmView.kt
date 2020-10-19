package com.kryptkode.farmz.screens.farm.capturefarm.view

import com.google.android.gms.maps.MapView
import com.kryptkode.farmz.screens.common.view.BaseObservableViewMvc
import com.kryptkode.farmz.screens.farm.model.UiFarmLocation

abstract class CaptureFarmView : BaseObservableViewMvc<CaptureFarmView.Listener>() {

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

}