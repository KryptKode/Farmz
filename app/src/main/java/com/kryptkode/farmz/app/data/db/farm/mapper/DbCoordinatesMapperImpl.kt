package com.kryptkode.farmz.app.data.db.farm.mapper

import com.kryptkode.farmz.app.data.db.farm.DbFarmCoordinates
import com.kryptkode.farmz.app.domain.farm.FarmCoordinates
import javax.inject.Inject

class DbCoordinatesMapperImpl
@Inject constructor(
    private val dbLocationMapper: DbLocationMapper
) : DbCoordinatesMapper {
    override fun mapDbToDomain(coordinates: DbFarmCoordinates): FarmCoordinates {
        return FarmCoordinates(
            coordinates.coordinates.map {
                dbLocationMapper.mapDbToDomain(it)
            }
        )
    }

    override fun mapDomainToDb(coordinates: FarmCoordinates): DbFarmCoordinates {
        return DbFarmCoordinates(
            coordinates.coordinates.map {
                dbLocationMapper.mapDomainToDb(it)
            }
        )
    }
}