package com.kryptkode.farmz.app.data.db.farm.mapper

import com.kryptkode.farmz.app.data.db.farm.DbLocation
import com.kryptkode.farmz.app.domain.farm.Location
import javax.inject.Inject

class DbLocationMapperImpl @Inject constructor() : DbLocationMapper {
    override fun mapDbToDomain(location: DbLocation): Location {
        return Location(
            location.latitude,
            location.longitude
        )
    }

    override fun mapDomainToDb(location: Location): DbLocation {
        return DbLocation(
            location.latitude,
            location.longitude
        )
    }
}