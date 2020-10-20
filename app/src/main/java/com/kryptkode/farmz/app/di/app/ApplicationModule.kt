package com.kryptkode.farmz.app.di.app

import android.app.Application
import com.kryptkode.farmz.app.dispatcher.AppDispatchers
import com.kryptkode.farmz.app.dispatcher.AppDispatchersImpl
import com.kryptkode.farmz.app.logger.Logger
import com.kryptkode.farmz.app.logger.LoggerImpl
import com.kryptkode.farmz.datareturn.ScreenDataReturnBuffer
import com.kryptkode.farmz.datareturn.ScreenDataReturnBufferImpl
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

    @ApplicationScope
    @Provides
    fun provideAppDispatcher(): AppDispatchers {
        return AppDispatchersImpl()
    }

    @ApplicationScope
    @Provides
    fun provideScreenReturnBuffer(): ScreenDataReturnBuffer {
        return ScreenDataReturnBufferImpl()
    }

}