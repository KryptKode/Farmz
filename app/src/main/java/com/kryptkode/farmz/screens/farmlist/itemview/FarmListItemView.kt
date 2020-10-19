package com.kryptkode.farmz.screens.farmlist.itemview

import com.kryptkode.farmz.screens.capturefarm.model.UiFarm
import com.kryptkode.farmz.screens.common.view.BaseObservableViewMvc

abstract class FarmListItemView: BaseObservableViewMvc<FarmListItemView.Listener>() {
    interface Listener{
        fun onFarmClick(item:UiFarm)
    }

    abstract fun bind(item:UiFarm)
}