
package com.kryptkode.farmz.app.data.db.farm

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DbFarmLocation(
    @field:Json(name = "lat") val latitude: Double,
    @field:Json(name = "lng") val longitude: Double
)