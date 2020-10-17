package com.kryptkode.farmz.app.di.app.repo

import com.kryptkode.farmz.app.data.auth.AuthRepositoryImpl
import com.kryptkode.farmz.app.data.keyvaluestore.KeyValueStore
import com.kryptkode.farmz.app.di.app.ApplicationScope
import com.kryptkode.farmz.app.dispatcher.AppDispatchers
import com.kryptkode.farmz.app.domain.AuthRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    @ApplicationScope
    fun provideAuthRepo(dispatchers: AppDispatchers, keyValueStore: KeyValueStore): AuthRepository {
        return AuthRepositoryImpl(dispatchers, keyValueStore)
    }
}