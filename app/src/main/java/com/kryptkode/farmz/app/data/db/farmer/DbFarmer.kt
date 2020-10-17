package com.kryptkode.farmz.app.data.db.farmer

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kryptkode.farmz.app.data.db.farmer.DbFarmer.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class DbFarmer(
    @PrimaryKey(autoGenerate = false) val id: String,
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
) {
    companion object {
        const val TABLE_NAME = "farmers"
    }
}