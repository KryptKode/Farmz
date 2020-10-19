package com.kryptkode.farmz.screens.dashboard.view

import androidx.paging.PagingData
import com.kryptkode.farmz.screens.common.view.BaseObservableViewMvc
import com.kryptkode.farmz.screens.farm.model.UiFarm

abstract class DashboardViewMvc : BaseObservableViewMvc<DashboardViewMvc.Listener>() {

    interface Listener {
        fun onViewAllFarmsClick()
        fun onItemClick(item: UiFarm)
        fun onLoadError(s: String)
    }

    abstract fun bindLastCapturedFarms(count: Int, date: String)
    abstract fun bindLastCapturedFarmers(count: Int, date: String)
    abstract suspend fun bindFarms(data: PagingData<UiFarm>)

    abstract fun showLastCapturedFarmersLoading()
    abstract fun hideLastCapturedFarmersLoading()
    abstract fun showLastCapturedFarmsLoading()
    abstract fun hidLastCapturedFarmsLoading()
}