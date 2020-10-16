package com.kryptkode.farmz.app.data.mock

import com.kryptkode.farmz.app.data.db.farmer.DbFarmer
import com.kryptkode.farmz.app.domain.Farmer
import com.kryptkode.farmz.utils.MockDataFactory.randomString

class FarmerDataFactory {

    fun createMockDbFarmer(): DbFarmer {
        return DbFarmer(
            randomString(),
            randomString(),
            randomString(),
            randomString(),
            randomString(),
            randomString(),
            randomString(),
            randomString(),
            randomString(),
            randomString(),
            randomString(),
            randomString(),
            randomString(),
            randomString(),
            randomString(),
            randomString(),
            randomString(),
            randomString(),
            randomString(),
            randomString(),
            randomString(),
            randomString(),
            randomString(),
            randomString(),
            randomString(),
        )
    }

    fun createMockFarmer(): Farmer {
        return Farmer(
            randomString(),
            randomString(),
            randomString(),
            randomString(),
            randomString(),
            randomString(),
            randomString(),
            randomString(),
            randomString(),
            randomString(),
            randomString(),
            randomString(),
            randomString(),
            randomString(),
            randomString(),
            randomString(),
            randomString(),
            randomString(),
            randomString(),
            randomString(),
            randomString(),
            randomString(),
            randomString(),
            randomString(),
            randomString(),
        )
    }
}