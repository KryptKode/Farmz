package com.kryptkode.farmz.screens.farm.farmlist.view

import androidx.paging.PagingData
import com.kryptkode.farmz.screens.common.view.BaseObservableViewMvc
import com.kryptkode.farmz.screens.farm.model.UiFarm

abstract class FarmListViewMvc: BaseObservableViewMvc<FarmListViewMvc.Listener>() {
    interface  Listener {
        fun onItemClick(item: UiFarm)
        fun onLoadError(error: String)
    }

    abstract suspend fun bindFarms(farms:PagingData<UiFarm>)
}