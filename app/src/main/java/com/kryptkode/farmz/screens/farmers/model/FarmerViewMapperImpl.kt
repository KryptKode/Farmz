package com.kryptkode.farmz.screens.farmers.model

import com.kryptkode.farmz.app.domain.Farmer
import javax.inject.Inject

class FarmerViewMapperImpl @Inject constructor() : FarmerViewMapper {

    override fun mapDomainToView(farmer: Farmer): FarmerView {
        return FarmerView(
            farmer.id,
            farmer.registrationNumber,
            farmer.bvn,
            farmer.firstName,
            farmer.middleName,
            farmer.surname,
            farmer.dateOfBirth,
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
            farmer.issueDate,
            farmer.expiryDate,
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
            farmerView.dateOfBirth,
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
            farmerView.issueDate,
            farmerView.expiryDate,
            farmerView.idImage,
            farmerView.passportPhoto,
            farmerView.fingerprint,
        )
    }
}