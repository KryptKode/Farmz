package com.kryptkode.farmz.screens.common

import android.view.LayoutInflater
import com.kryptkode.farmz.screens.infodialog.view.InfoDialogViewMvc
import com.kryptkode.farmz.screens.infodialog.view.InfoDialogViewMvcImpl

class DialogViewFactory(private val layoutInflater: LayoutInflater) {

    fun getInfoDialogView(): InfoDialogViewMvc {
        return InfoDialogViewMvcImpl(layoutInflater)
    }

}