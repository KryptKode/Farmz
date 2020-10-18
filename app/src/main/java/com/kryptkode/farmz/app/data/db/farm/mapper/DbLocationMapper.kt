package com.kryptkode.farmz.app.data.db.farm.mapper

import com.kryptkode.farmz.app.data.db.farm.DbLocation
import com.kryptkode.farmz.app.domain.farm.Location

interface DbLocationMapper {
    fun mapDbToDomain(location: DbLocation): Location
    fun mapDomainToDb(location: Location): DbLocation
}