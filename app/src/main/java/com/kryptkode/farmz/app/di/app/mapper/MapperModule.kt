package com.kryptkode.farmz.app.di.app.mapper

import com.kryptkode.farmz.app.di.app.ApplicationScope
import com.kryptkode.farmz.screens.farm.model.UiFarmMapper
import com.kryptkode.farmz.screens.farm.model.UiFarmMapperImpl
import com.kryptkode.farmz.screens.farmers.model.FarmerViewMapper
import com.kryptkode.farmz.screens.farmers.model.FarmerViewMapperImpl
import dagger.Binds
import dagger.Module

@Module
interface MapperModule {

    @ApplicationScope
    @Binds
    fun bindFarmerViewMapper(farmerViewMapperImpl: FarmerViewMapperImpl): FarmerViewMapper

    @ApplicationScope
    @Binds
    fun bindUiFarmMapper(farmerViewMapperImpl: UiFarmMapperImpl): UiFarmMapper
}