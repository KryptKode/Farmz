package com.kryptkode.farmz.app.di.app.network

import com.kryptkode.farmz.app.data.network.mapper.FarmerApiMapper
import com.kryptkode.farmz.app.data.network.mapper.FarmerApiMapperImpl
import com.kryptkode.farmz.app.di.app.ApplicationScope
import dagger.Binds
import dagger.Module

@Module
interface MapperModule {

    @Binds
    @ApplicationScope
    fun bindFarmerMapperModule(farmerApiMapper: FarmerApiMapperImpl): FarmerApiMapper
}