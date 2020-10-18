package com.kryptkode.farmz.app.data.db.farm.mapper

import com.kryptkode.farmz.app.data.db.farm.DbFarm
import com.kryptkode.farmz.app.domain.farm.Farm
import javax.inject.Inject

class FarmDbMapperImpl @Inject constructor(
    private val coordinatesMapper: DbCoordinatesMapper
) : FarmDbMapper {

    override fun mapDbToDomain(farm: DbFarm): Farm {
        return Farm(
            farm.id,
            farm.farmerId,
            farm.name,
            farm.location,
            coordinatesMapper.mapDbToDomain(farm.dbFarmCoordinates)
        )
    }

    override fun mapDomainToDb(farm: Farm): DbFarm {
        return DbFarm(
            farm.id,
            farm.farmerId,
            farm.name,
            farm.location,
            coordinatesMapper.mapDomainToDb(farm.farmCoordinates)
        )
    }
}