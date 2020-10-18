package com.kryptkode.farmz.screens.farmers.view

import com.kryptkode.farmz.screens.common.view.BaseObservableViewMvc
import com.kryptkode.farmz.screens.farmers.model.FarmerView

abstract class FarmerListItemView : BaseObservableViewMvc<FarmerListItemView.Listener>() {

    interface Listener {
        fun onItemClick(item: FarmerView)
    }

    abstract fun bindFarmer(item: FarmerView?)
}