package com.kryptkode.farmz.app

import com.kryptkode.farmz.app.di.app.ApplicationComponent


class TestApp : App(){

    override val applicationComponent: ApplicationComponent
        get() = DaggerTestComponent
            .builder()
            .bindApplication(this)
            .build()
}