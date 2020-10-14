package com.kryptkode.flashalerts.app.di.app

import android.app.Application
import com.kryptkode.flashalerts.app.logger.Logger
import com.kryptkode.flashalerts.app.logger.LoggerImpl
import dagger.Module
import dagger.Provides

/**
 * Created by kryptkode on 5/21/2020.
 */
@Module
class ApplicationModule(private val application: Application) {

    @ApplicationScope
    @Provides
    fun provideApplication(): Application {
        return application
    }

    @ApplicationScope
    @Provides
    fun provideLogger(): Logger {
        return LoggerImpl()
    }


}