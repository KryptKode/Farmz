package com.kryptkode.farmz.screens.common.view

import android.content.Context
import android.view.View
import androidx.annotation.StringRes

abstract class BaseViewMvc : ViewMvc {

    protected fun <T : View> findViewById(id: Int): T {
        return rootView.findViewById(id)
    }

    protected val context: Context
        get() = rootView.context

    protected fun getString(@StringRes id: Int, vararg any: Any): String {
        return context.getString(id, *any)
    }
}