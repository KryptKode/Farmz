package com.kryptkode.farmz.screens.farmers.view

import androidx.paging.LoadState
import com.kryptkode.farmz.screens.common.view.BaseObservableViewMvc

abstract class FarmerLoadingView : BaseObservableViewMvc<FarmerLoadingView.Listener>(){
    interface Listener {
        fun onRetry()
    }

    abstract fun bindLoadState(loadState: LoadState)

}