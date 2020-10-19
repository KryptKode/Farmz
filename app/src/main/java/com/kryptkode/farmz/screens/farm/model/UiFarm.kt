package com.kryptkode.farmz.screens.farm.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UiFarm(
    val id: Int,
    val farmerId: String,
    val name: String,
    val location: String,
    val farmCoordinates: List<UiFarmLocation>,
    val dateLastUpdated: String,
    val dateCreated: String
) : Parcelable

@Parcelize
data class UiFarmLocation(
    val latitude: Double,
    val longitude: Double
) : Parcelable