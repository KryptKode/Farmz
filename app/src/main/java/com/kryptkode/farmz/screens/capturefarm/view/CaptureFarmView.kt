package com.kryptkode.farmz.screens.capturefarm.view

import com.kryptkode.farmz.screens.common.view.BaseObservableViewMvc

abstract class CaptureFarmView : BaseObservableViewMvc<CaptureFarmView.Listener>() {

    interface Listener {
        fun onAddLocation()
        fun onSave()
        fun onBackClick()
    }

    abstract fun showLocationError(message: String)
    abstract fun showNameError(message: String)

    abstract fun clearErrors()

    abstract fun showLoading()
    abstract fun hideLoading()
    abstract fun onSelectLocation()

}