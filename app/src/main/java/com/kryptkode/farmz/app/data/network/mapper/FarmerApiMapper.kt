package com.kryptkode.farmz.app.data.network.mapper

import com.kryptkode.farmz.app.data.network.response.FarmerRemote
import com.kryptkode.farmz.app.domain.Farmer

interface FarmerApiMapper {
    fun mapRemoteToDomain(farmerRemote: FarmerRemote): Farmer
}