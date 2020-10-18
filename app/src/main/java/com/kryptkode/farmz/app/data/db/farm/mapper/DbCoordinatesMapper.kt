package com.kryptkode.farmz.app.data.db.farm.mapper

import com.kryptkode.farmz.app.data.db.farm.DbFarmCoordinates
import com.kryptkode.farmz.app.domain.farm.FarmCoordinates

interface DbCoordinatesMapper {
    fun mapDbToDomain(coordinates: DbFarmCoordinates): FarmCoordinates
    fun mapDomainToDb(coordinates: FarmCoordinates): DbFarmCoordinates
}