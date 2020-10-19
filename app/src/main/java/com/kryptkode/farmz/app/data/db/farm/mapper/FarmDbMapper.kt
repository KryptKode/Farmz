package com.kryptkode.farmz.app.data.db.farm.mapper

import com.kryptkode.farmz.app.data.db.farm.DbFarm
import com.kryptkode.farmz.app.domain.farm.Farm

interface FarmDbMapper {
    fun mapDbToDomain(farm: DbFarm): Farm
    fun mapDomainToDb(farm: Farm): DbFarm
}