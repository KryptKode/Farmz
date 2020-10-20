package com.kryptkode.farmz.navigation

import android.view.View
import androidx.navigation.NavController

interface NavControllerProvider {
    fun getNavController(): NavController
    fun getNavView(): View?
}