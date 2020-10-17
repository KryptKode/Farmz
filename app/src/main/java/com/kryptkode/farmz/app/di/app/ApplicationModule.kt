package com.kryptkode.farmz.app.di.app

import android.app.Application
import com.kryptkode.farmz.app.data.keyvaluestore.KeyValueStore
import com.kryptkode.farmz.app.data.keyvaluestore.KeyValueStoreImpl
import com.kryptkode.farmz.app.dispatcher.AppDispatchers
import com.kryptkode.farmz.app.dispatcher.AppDispatchersImpl
import com.kryptkode.farmz.app.logger.Logger
import com.kryptkode.farmz.app.logger.LoggerImpl
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

    @Provides
    @ApplicationScope
    fun provideKeyValueStore(application: Application): KeyValueStore {
        return KeyValueStoreImpl(application)
    }


}