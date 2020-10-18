package com.kryptkode.farmz.screens.farmers.model

import com.kryptkode.farmz.app.domain.Farmer

interface FarmerViewMapper {
    fun mapViewToDomain(farmerView: FarmerView): Farmer
    fun mapDomainToView(farmer: Farmer): FarmerView
}