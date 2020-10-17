package com.kryptkode.farmz.app.data.db.farmer

import com.kryptkode.farmz.app.domain.Farmer

interface FarmerDbMapper {
    fun mapDbToDomain(dbFarmer: DbFarmer): Farmer
    fun mapDomainToDb(farmer: Farmer): DbFarmer
}