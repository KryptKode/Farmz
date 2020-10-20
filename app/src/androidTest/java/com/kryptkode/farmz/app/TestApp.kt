package com.kryptkode.farmz.app

import com.kryptkode.farmz.app.di.app.ApplicationComponent
import com.kryptkode.farmz.di.DaggerTestComponent


class TestApp : App() {

    override val applicationComponent: ApplicationComponent
        get() = DaggerTestComponent
            .builder()
            .build()
}