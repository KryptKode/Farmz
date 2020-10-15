package com.kryptkode.farmz.app.di.controller

import com.kryptkode.farmz.screens.home.MainActivity
import com.kryptkode.farmz.screens.splash.SplashActivity
import dagger.Subcomponent

/**
 * Created by kryptkode on 5/21/2020.
 */
@ControllerScope
@Subcomponent(
    modules = [
        ControllerModule::class,
        ScreensNavigatorModule::class
    ]
)
interface ControllerComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainActivity: SplashActivity)
}