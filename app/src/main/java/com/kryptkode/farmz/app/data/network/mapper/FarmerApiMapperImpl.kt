package com.kryptkode.farmz.app.data.network.mapper

import com.kryptkode.farmz.app.data.network.response.FarmerRemote
import com.kryptkode.farmz.app.domain.Farmer
import javax.inject.Inject

class FarmerApiMapperImpl @Inject constructor() : FarmerApiMapper {

    override fun mapRemoteToDomain(farmerRemote: FarmerRemote): Farmer {
        return Farmer(
            farmerRemote.id,
            farmerRemote.registrationNumber,
            farmerRemote.bvn,
            farmerRemote.firstName,
            farmerRemote.middleName,
            farmerRemote.surname,
            farmerRemote.dateOfBirth,
            farmerRemote.gender,
            farmerRemote.nationality,
            farmerRemote.occupation,
            farmerRemote.maritalStatus,
            farmerRemote.spouseName,
            farmerRemote.address,
            farmerRemote.city,
            farmerRemote.lga,
            farmerRemote.state,
            farmerRemote.mobileNumber,
            farmerRemote.emailAddress,
            farmerRemote.idType,
            farmerRemote.idNumber,
            farmerRemote.issueDate,
            farmerRemote.expiryDate,
            farmerRemote.idImage,
            farmerRemote.passportPhoto,
            farmerRemote.fingerprint,
        )
    }

}