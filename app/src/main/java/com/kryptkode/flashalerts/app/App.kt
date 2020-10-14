package com.kryptkode.flashalerts.app

import androidx.multidex.MultiDexApplication
import com.kryptkode.flashalerts.app.di.app.ApplicationComponent
import com.kryptkode.flashalerts.app.di.app.ApplicationModule
import com.kryptkode.flashalerts.app.di.app.DaggerApplicationComponent

/**
 * Created by kryptkode on 5/21/2020.
 */
class App : MultiDexApplication() {
    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }
}