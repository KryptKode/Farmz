package com.kryptkode.farmz.app.di.controller.navigator

import com.kryptkode.farmz.app.di.controller.ControllerScope
import com.kryptkode.farmz.navigation.login.LoginNavigator
import com.kryptkode.farmz.navigation.login.LoginNavigatorImpl
import dagger.Binds
import dagger.Module

@Module
interface NavigatorModule {

    @Binds
    @ControllerScope
    fun loginNavigator(loginNavigatorImpl: LoginNavigatorImpl):LoginNavigator

}