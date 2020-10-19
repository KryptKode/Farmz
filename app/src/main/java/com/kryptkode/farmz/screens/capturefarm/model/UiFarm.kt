package com.kryptkode.farmz.screens.capturefarm.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UiFarm(
    val id: Int,
    val farmerId: String,
    val name: String,
    val location: String,
    val farmCoordinates: List<UiFarmLocation>
) : Parcelable

@Parcelize
data class UiFarmLocation(
    val latitude: Double,
    val longitude: Double
) : Parcelable