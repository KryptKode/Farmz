package com.kryptkode.flashalerts.app.di.service

import android.app.Service
import android.content.Context
import dagger.Module
import dagger.Provides

/**
 * Created by kryptkode on 5/21/2020.
 */
@Module
class ServiceModule(private val service: Service) {

    @Provides
    @ServiceScope
    fun provideContext(): Context {
        return service
    }

    @Provides
    @ServiceScope
    fun provideService(): Service {
        return service
    }
}