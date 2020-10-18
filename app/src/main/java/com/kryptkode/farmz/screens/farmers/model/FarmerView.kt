package com.kryptkode.farmz.screens.farmers.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FarmerView (
    val id: String,
    val registrationNumber: String,
    val bvn: String,
    val firstName: String,
    val middleName: String,
    val surname: String,
    val dateOfBirth: String,
    val gender: String,
    val nationality: String,
    val occupation: String,
    val maritalStatus: String,
    val spouseName: String,
    val address: String,
    val city: String,
    val lga: String,
    val state: String,
    val mobileNumber: String,
    val emailAddress: String,
    val idType: String,
    val idNumber: String,
    val issueDate: String,
    val expiryDate: String,
    val idImage: String,
    val passportPhoto: String,
    val fingerprint: String,
): Parcelable