package com.kryptkode.farmz.app

import androidx.multidex.MultiDexApplication
import com.google.android.gms.maps.MapsInitializer
import com.kryptkode.farmz.app.di.app.ApplicationComponent
import com.kryptkode.farmz.app.di.app.ApplicationModule
import com.kryptkode.farmz.app.di.app.DaggerApplicationComponent

/**
 * Created by kryptkode on 5/21/2020.
 */
open class App : MultiDexApplication() {

    open val applicationComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        MapsInitializer.initialize(this)
    }
}