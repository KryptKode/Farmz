package com.kryptkode.farmz.app.data.network.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FarmersResponse(
    @field:Json(name = "status") val status: String,
    @field:Json(name = "data") val data: FarmersResponseData,
)

@JsonClass(generateAdapter = true)
data class FarmersResponseData(
    @field:Json(name = "farmers") val farmers: List<FarmerRemote>,
    @field:Json(name = "totalRec") val totalRecords: Int,
    @field:Json(name = "imageBaseUrl") val imageBaseUrl: String,
)

@JsonClass(generateAdapter = true)
data class FarmerRemote(
    @field:Json(name = "farmer_id") val id: String,
    @field:Json(name = "reg_no") val registrationNumber: String,
    @field:Json(name = "bvn") val bvn: String,
    @field:Json(name = "first_name") val firstName: String,
    @field:Json(name = "middle_name") val middleName: String,
    @field:Json(name = "surname") val surname: String,
    @field:Json(name = "dob") val dateOfBirth: String,
    @field:Json(name = "gender") val gender: String,
    @field:Json(name = "nationality") val nationality: String,
    @field:Json(name = "occupation") val occupation: String,
    @field:Json(name = "marital_status") val maritalStatus: String,
    @field:Json(name = "spouse_name") val spouseName: String,
    @field:Json(name = "address") val address: String,
    @field:Json(name = "city") val city: String,
    @field:Json(name = "lga") val lga: String,
    @field:Json(name = "state") val state: String,
    @field:Json(name = "mobile_no") val mobileNumber: String,
    @field:Json(name = "email_address") val emailAddress: String,
    @field:Json(name = "id_type") val idType: String,
    @field:Json(name = "id_no") val idNumber: String,
    @field:Json(name = "issue_date") val issueDate: String,
    @field:Json(name = "expiry_date") val expiryDate: String,
    @field:Json(name = "id_image") val idImage: String,
    @field:Json(name = "passport_photo") val passportPhoto: String,
    @field:Json(name = "fingerprint") val fingerprint: String,
)