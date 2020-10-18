package com.kryptkode.farmz.app.domain.farm

data class Farm(
    val id:Int,
    val farmerId: String,
    val name: String,
    val location: String,
    val farmCoordinates: FarmCoordinates
)