package com.kryptkode.farmz.app.di.app.mapper

import com.kryptkode.farmz.app.di.app.ApplicationScope
import com.kryptkode.farmz.screens.farmers.model.FarmerViewMapper
import com.kryptkode.farmz.screens.farmers.model.FarmerViewMapperImpl
import dagger.Binds
import dagger.Module

@Module
interface MapperModule {

    @ApplicationScope
    @Binds
    fun bindFarmerViewMapper(farmerViewMapperImpl: FarmerViewMapperImpl): FarmerViewMapper
}