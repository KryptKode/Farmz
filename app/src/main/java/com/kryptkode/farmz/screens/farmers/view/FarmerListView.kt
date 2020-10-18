package com.kryptkode.farmz.screens.farmers.view

import androidx.paging.PagingData
import com.kryptkode.farmz.screens.common.view.BaseObservableViewMvc
import com.kryptkode.farmz.screens.farmers.model.FarmerView

abstract class FarmerListView : BaseObservableViewMvc<FarmerListView.Listener>(){

    interface Listener {
        fun onItemClick(item: FarmerView)
        fun onLoadError(message:String)
    }

    abstract suspend fun bindFarmers(items: PagingData<FarmerView>)
}