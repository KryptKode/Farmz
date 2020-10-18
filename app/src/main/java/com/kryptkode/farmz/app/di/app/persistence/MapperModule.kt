package com.kryptkode.farmz.app.di.app.persistence

import com.kryptkode.farmz.app.data.db.farmer.FarmerDbMapper
import com.kryptkode.farmz.app.data.db.farmer.FarmerDbMapperImpl
import com.kryptkode.farmz.app.di.app.ApplicationScope
import dagger.Binds
import dagger.Module

@Module
interface MapperModule {

    @Binds
    @ApplicationScope
    fun bindDbFarmerMapperModule(dbMapperImpl: FarmerDbMapperImpl): FarmerDbMapper
}