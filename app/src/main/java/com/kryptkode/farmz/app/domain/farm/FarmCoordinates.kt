package com.kryptkode.farmz.app.domain.farm

data class FarmCoordinates(
    val coordinates: List<Location>
)

data class Location(
    val latitude: Double,
    val longitude: Double
)