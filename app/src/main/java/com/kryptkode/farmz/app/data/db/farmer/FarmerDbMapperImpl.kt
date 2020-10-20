package com.kryptkode.farmz.app.data.db.farmer

import com.kryptkode.farmz.app.domain.Farmer
import javax.inject.Inject

class FarmerDbMapperImpl @Inject constructor() : FarmerDbMapper {

    override fun mapDbToDomain(dbFarmer: DbFarmer): Farmer {
        return Farmer(
            dbFarmer.id,
            dbFarmer.registrationNumber,
            dbFarmer.bvn,
            dbFarmer.firstName,
            dbFarmer.middleName,
            dbFarmer.surname,
            dbFarmer.dateOfBirth,
            dbFarmer.gender,
            dbFarmer.nationality,
            dbFarmer.occupation,
            dbFarmer.maritalStatus,
            dbFarmer.spouseName,
            dbFarmer.address,
            dbFarmer.city,
            dbFarmer.lga,
            dbFarmer.state,
            dbFarmer.mobileNumber,
            dbFarmer.emailAddress,
            dbFarmer.idType,
            dbFarmer.idNumber,
            dbFarmer.issueDate,
            dbFarmer.expiryDate,
            dbFarmer.idImage,
            dbFarmer.passportPhoto,
            dbFarmer.fingerprint,
        )
    }

    override fun mapDomainToDb(farmer: Farmer): DbFarmer {
        return DbFarmer(
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
}
