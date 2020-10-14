package com.kryptkode.flashalerts.app.di.controller

import com.kryptkode.flashalerts.screens.home.MainActivity
import com.kryptkode.flashalerts.screens.splash.SplashActivity
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