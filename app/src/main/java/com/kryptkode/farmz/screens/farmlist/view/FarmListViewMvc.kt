package com.kryptkode.farmz.screens.farmlist.view

import androidx.paging.PagingData
import com.kryptkode.farmz.screens.capturefarm.model.UiFarm
import com.kryptkode.farmz.screens.common.view.BaseObservableViewMvc

abstract class FarmListViewMvc: BaseObservableViewMvc<FarmListViewMvc.Listener>() {
    interface  Listener {
        fun onItemClick(item: UiFarm)
        fun onRefresh()
        fun onLoadError(error: String)
    }

    abstract suspend fun bindFarms(farms:PagingData<UiFarm>)
}