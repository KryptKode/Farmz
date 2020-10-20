package com.kryptkode.farmz.app.domain.farm

import java.util.*

data class Farm(
    val id: Int,
    val farmerId: String,
    val name: String,
    val location: String,
    val farmCoordinates: List<FarmLocation>,
    val dateLastUpdated: Date,
    val dateCreated: Date
)