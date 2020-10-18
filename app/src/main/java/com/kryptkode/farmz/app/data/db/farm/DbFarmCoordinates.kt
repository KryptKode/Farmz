package com.kryptkode.farmz.app.data.db.farm

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DbFarmCoordinates(
    @field:Json(name = "coordinates") val coordinates: List<DbLocation>
)

@JsonClass(generateAdapter = true)
data class DbLocation(
    @field:Json(name = "lat") val latitude: Double,
    @field:Json(name = "lng") val longitude: Double
)