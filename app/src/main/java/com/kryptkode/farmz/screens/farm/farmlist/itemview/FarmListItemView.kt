package com.kryptkode.farmz.screens.farm.farmlist.itemview

import com.kryptkode.farmz.screens.common.view.BaseObservableViewMvc
import com.kryptkode.farmz.screens.farm.model.UiFarm

abstract class FarmListItemView: BaseObservableViewMvc<FarmListItemView.Listener>() {
    interface Listener{
        fun onFarmClick(item:UiFarm)
    }

    abstract fun bind(item:UiFarm?)
}