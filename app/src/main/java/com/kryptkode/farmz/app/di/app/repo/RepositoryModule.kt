package com.kryptkode.farmz.app.di.app.repo

import com.kryptkode.farmz.app.data.FarmerRemoteMediator
import com.kryptkode.farmz.app.data.FarmerRepositoryImpl
import com.kryptkode.farmz.app.data.auth.AuthRepositoryImpl
import com.kryptkode.farmz.app.data.db.AppDatabase
import com.kryptkode.farmz.app.data.db.farmer.FarmerDbMapper
import com.kryptkode.farmz.app.data.keyvaluestore.KeyValueStore
import com.kryptkode.farmz.app.di.app.ApplicationScope
import com.kryptkode.farmz.app.dispatcher.AppDispatchers
import com.kryptkode.farmz.app.domain.AuthRepository
import com.kryptkode.farmz.app.domain.farmer.FarmerRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    @ApplicationScope
    fun provideAuthRepo(dispatchers: AppDispatchers, keyValueStore: KeyValueStore): AuthRepository {
        return AuthRepositoryImpl(dispatchers, keyValueStore)
    }

    @Provides
    @ApplicationScope
    fun provideFarmerRepo(
        remoteMediator: FarmerRemoteMediator,
        appDatabase: AppDatabase,
        farmerDbMapper: FarmerDbMapper
    ): FarmerRepository {
        return FarmerRepositoryImpl(remoteMediator, appDatabase, farmerDbMapper)
    }
}