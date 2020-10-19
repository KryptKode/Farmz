package com.kryptkode.farmz.screens.farmlist.view

import com.kryptkode.farmz.screens.capturefarm.model.UiFarm
import com.kryptkode.farmz.screens.common.view.BaseObservableViewMvc

abstract class FarmListViewMvc: BaseObservableViewMvc<FarmListViewMvc.Listener>() {
    interface  Listener {
        fun onItemClick(item: UiFarm)
        fun onRefresh()
    }

    abstract fun bindFarms(farms:List<UiFarm>)
}