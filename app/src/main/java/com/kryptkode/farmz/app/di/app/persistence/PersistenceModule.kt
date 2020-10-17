package com.kryptkode.farmz.app.di.app.persistence

import android.app.Application
import com.kryptkode.farmz.app.data.db.AppDatabase
import com.kryptkode.farmz.app.data.keyvaluestore.KeyValueStore
import com.kryptkode.farmz.app.data.keyvaluestore.KeyValueStoreImpl
import com.kryptkode.farmz.app.di.app.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class PersistenceModule {

    @ApplicationScope
    @Provides
    fun provideAppDb(application: Application): AppDatabase {
        return AppDatabase.getInstance(application)
    }

    @Provides
    @ApplicationScope
    fun provideKeyValueStore(application: Application): KeyValueStore {
        return KeyValueStoreImpl(application)
    }

}