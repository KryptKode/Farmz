package com.kryptkode.farmz.screens.capturefarm.model

import com.kryptkode.farmz.app.domain.farm.Farm
import com.kryptkode.farmz.app.domain.farm.FarmLocation
import javax.inject.Inject

class UiFarmMapperImpl @Inject constructor() : UiFarmMapper {

    override fun mapViewToDomain(farm: UiFarm): Farm {
        return Farm(
            farm.id,
            farm.farmerId,
            farm.name,
            farm.location,
            farm.farmCoordinates.map {
                FarmLocation(
                    it.latitude,
                    it.longitude
                )
            }
        )
    }

    override fun mapDomainToView(farm: Farm): UiFarm {
        return UiFarm(
            farm.id,
            farm.farmerId,
            farm.name,
            farm.location,
            farm.farmCoordinates.map {
                UiFarmLocation(it.latitude, it.longitude)
            }
        )
    }
}