package com.kryptkode.farmz.app.di.controller

import com.kryptkode.farmz.app.di.controller.navigator.NavigatorModule
import com.kryptkode.farmz.app.di.controller.util.UtilModule
import com.kryptkode.farmz.app.di.controller.viewmodel.ViewModelModule
import com.kryptkode.farmz.screens.MainActivity
import com.kryptkode.farmz.screens.login.LoginFragment
import com.kryptkode.farmz.screens.splash.SplashActivity
import dagger.Subcomponent

/**
 * Created by kryptkode on 5/21/2020.
 */
@ControllerScope
@Subcomponent(
    modules = [
        ControllerModule::class,
        ViewModelModule::class,
        NavigatorModule::class,
        UtilModule::class
    ]
)
interface ControllerComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainActivity: SplashActivity)
    fun inject(loginFragment: LoginFragment)
}