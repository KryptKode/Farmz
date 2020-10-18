package com.kryptkode.farmz.screens.farmers.model

import com.kryptkode.farmz.app.domain.Farmer
import com.kryptkode.farmz.app.utils.PhoneNumberSanitizer
import com.kryptkode.farmz.app.utils.date.ServerDisplayedDateConverter
import javax.inject.Inject

class FarmerViewMapperImpl @Inject constructor(
    private val phoneNumberSanitizer: PhoneNumberSanitizer,
    private val serverDisplayedDateConverter: ServerDisplayedDateConverter
) : FarmerViewMapper {

    override fun mapDomainToView(farmer: Farmer): FarmerView {
        return FarmerView(
            farmer.id,
            farmer.registrationNumber,
            farmer.bvn,
            farmer.firstName,
            farmer.middleName,
            farmer.surname,
            getDisplayedDate(farmer.dateOfBirth),
            farmer.gender,
            farmer.nationality,
            farmer.occupation,
            farmer.maritalStatus,
            farmer.spouseName,
            farmer.address,
            farmer.city,
            farmer.lga,
            farmer.state,
            farmer.mobileNumber,
            farmer.emailAddress,
            farmer.idType,
            farmer.idNumber,
            getDisplayedDate(farmer.issueDate),
            getDisplayedDate(farmer.expiryDate),
            farmer.idImage,
            farmer.passportPhoto,
            farmer.fingerprint,
        )
    }

    override fun mapViewToDomain(farmerView: FarmerView): Farmer {
        return Farmer(
            farmerView.id,
            farmerView.registrationNumber,
            farmerView.bvn,
            farmerView.firstName,
            farmerView.middleName,
            farmerView.surname,
            getServerDate(farmerView.dateOfBirth),
            farmerView.gender,
            farmerView.nationality,
            farmerView.occupation,
            farmerView.maritalStatus,
            farmerView.spouseName,
            farmerView.address,
            farmerView.city,
            farmerView.lga,
            farmerView.state,
            farmerView.mobileNumber,
            farmerView.emailAddress,
            farmerView.idType,
            farmerView.idNumber,
            getServerDate(farmerView.issueDate),
            getServerDate(farmerView.expiryDate),
            farmerView.idImage,
            farmerView.passportPhoto,
            farmerView.fingerprint,
        )
    }


    private fun getServerDate(displayDate: String): String {
        return serverDisplayedDateConverter.convertDisplayedDateToServerDate(displayDate)
    }

    private fun getDisplayedDate(date: String): String {
        return serverDisplayedDateConverter.convertServerDateToDisplayedDate(date)
    }

    private fun getDisplayedPhone(phone: String): String {
        return phoneNumberSanitizer.formatWithCountryCode(phone)
    }


}