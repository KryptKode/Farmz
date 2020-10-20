package com.kryptkode.farmz.app.data.db.farm.mapper

import com.kryptkode.farmz.app.data.db.farm.DbFarm
import com.kryptkode.farmz.app.data.db.farm.DbFarmLocation
import com.kryptkode.farmz.app.domain.farm.Farm
import com.kryptkode.farmz.app.domain.farm.FarmLocation
import javax.inject.Inject

class FarmDbMapperImpl @Inject constructor(
) : FarmDbMapper {

    override fun mapDbToDomain(farm: DbFarm): Farm {
        return Farm(
            farm.id,
            farm.farmerId,
            farm.name,
            farm.location,
            farm.dbFarmCoordinates.map {
                FarmLocation(
                    it.latitude,
                    it.longitude
                )
            },
            farm.dateLastUpdated,
            farm.dateCreated
        )
    }

    override fun mapDomainToDb(farm: Farm): DbFarm {
        return DbFarm(
            farm.id,
            farm.farmerId,
            farm.name,
            farm.location,
            farm.farmCoordinates.map {
                DbFarmLocation(
                    it.latitude,
                    it.longitude
                )
            },
            farm.dateLastUpdated,
            farm.dateCreated
        )
    }
}