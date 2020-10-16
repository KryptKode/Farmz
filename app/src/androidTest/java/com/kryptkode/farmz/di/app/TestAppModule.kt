package com.kryptkode.farmz.di.app

import android.app.Application
import android.content.Context
import com.kryptkode.farmz.app.TestApp
import com.kryptkode.farmz.app.di.app.ApplicationScope
import dagger.Binds
import dagger.Module

@Module(includes = [])
abstract class TestAppModule {
    @Binds
    @ApplicationScope
    abstract fun provideApplicationContext(application: TestApp): Context


    @Binds
    @ApplicationScope
    abstract fun provideApplication(application: TestApp): Application
}