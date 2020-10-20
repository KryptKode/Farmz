package com.kryptkode.farmz.screens.infodialog.view

import com.kryptkode.farmz.screens.common.view.BaseObservableViewMvc
import com.kryptkode.farmz.screens.infodialog.Info

abstract class InfoDialogViewMvc : BaseObservableViewMvc<InfoDialogViewMvc.Listener>() {

    interface Listener {
        fun onPositiveButtonClick()
        fun onNeutralButtonClick()
        fun onNegativeButtonClick()
    }

    abstract fun bindInfo(info: Info)

}