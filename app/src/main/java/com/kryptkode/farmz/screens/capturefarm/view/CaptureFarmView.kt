package com.kryptkode.farmz.screens.capturefarm.view

import com.kryptkode.farmz.screens.common.view.BaseObservableViewMvc

abstract class CaptureFarmView : BaseObservableViewMvc<CaptureFarmView.Listener>() {

    interface Listener {
        fun onAddLocation()
        fun onSave()
    }
}