package com.kryptkode.farmz.app.data.db.farmer

import com.google.common.truth.Truth.assertThat
import com.kryptkode.farmz.utils.FarmerDataFactory
import org.junit.Before
import org.junit.Test

class FarmerDbMapperTest {

    private lateinit var farmerDbMapper: FarmerDbMapper

    private val farmerDataFactory = FarmerDataFactory()

    @Before
    fun setUp() {
        farmerDbMapper = FarmerDbMapperImpl()
    }

    @Test
    fun `mapDbToDomain maps all fields correctly`() {
        val dbFarmer = farmerDataFactory.createMockDbFarmer()
        val result = farmerDbMapper.mapDbToDomain(dbFarmer)

        assertThat(result.id).isEqualTo(dbFarmer.id)
        assertThat(result.registrationNumber).isEqualTo(dbFarmer.registrationNumber)
        assertThat(result.bvn).isEqualTo(dbFarmer.bvn)
        assertThat(result.firstName).isEqualTo(dbFarmer.firstName)
        assertThat(result.middleName).isEqualTo(dbFarmer.middleName)
        assertThat(result.surname).isEqualTo(dbFarmer.surname)
        assertThat(result.dateOfBirth).isEqualTo(dbFarmer.dateOfBirth)
        assertThat(result.gender).isEqualTo(dbFarmer.gender)
        assertThat(result.nationality).isEqualTo(dbFarmer.nationality)
        assertThat(result.occupation).isEqualTo(dbFarmer.occupation)
        assertThat(result.maritalStatus).isEqualTo(dbFarmer.maritalStatus)
        assertThat(result.spouseName).isEqualTo(dbFarmer.spouseName)
        assertThat(result.address).isEqualTo(dbFarmer.address)
        assertThat(result.city).isEqualTo(dbFarmer.city)
        assertThat(result.lga).isEqualTo(dbFarmer.lga)
        assertThat(result.state).isEqualTo(dbFarmer.state)
        assertThat(result.mobileNumber).isEqualTo(dbFarmer.mobileNumber)
        assertThat(result.emailAddress).isEqualTo(dbFarmer.emailAddress)
        assertThat(result.idType).isEqualTo(dbFarmer.idType)
        assertThat(result.idNumber).isEqualTo(dbFarmer.idNumber)
        assertThat(result.issueDate).isEqualTo(dbFarmer.issueDate)
        assertThat(result.expiryDate).isEqualTo(dbFarmer.expiryDate)
        assertThat(result.idImage).isEqualTo(dbFarmer.idImage)
        assertThat(result.passportPhoto).isEqualTo(dbFarmer.passportPhoto)
        assertThat(result.fingerprint).isEqualTo(dbFarmer.fingerprint)
    }


    @Test
    fun `mapDomainTo maps all fields correctly`() {
        val domainFarmer = farmerDataFactory.createMockFarmer()
        val result = farmerDbMapper.mapDomainToDb(domainFarmer)

        assertThat(result.id).isEqualTo(domainFarmer.id)
        assertThat(result.registrationNumber).isEqualTo(domainFarmer.registrationNumber)
        assertThat(result.bvn).isEqualTo(domainFarmer.bvn)
        assertThat(result.firstName).isEqualTo(domainFarmer.firstName)
        assertThat(result.middleName).isEqualTo(domainFarmer.middleName)
        assertThat(result.surname).isEqualTo(domainFarmer.surname)
        assertThat(result.dateOfBirth).isEqualTo(domainFarmer.dateOfBirth)
        assertThat(result.gender).isEqualTo(domainFarmer.gender)
        assertThat(result.nationality).isEqualTo(domainFarmer.nationality)
        assertThat(result.occupation).isEqualTo(domainFarmer.occupation)
        assertThat(result.maritalStatus).isEqualTo(domainFarmer.maritalStatus)
        assertThat(result.spouseName).isEqualTo(domainFarmer.spouseName)
        assertThat(result.address).isEqualTo(domainFarmer.address)
        assertThat(result.city).isEqualTo(domainFarmer.city)
        assertThat(result.lga).isEqualTo(domainFarmer.lga)
        assertThat(result.state).isEqualTo(domainFarmer.state)
        assertThat(result.mobileNumber).isEqualTo(domainFarmer.mobileNumber)
        assertThat(result.emailAddress).isEqualTo(domainFarmer.emailAddress)
        assertThat(result.idType).isEqualTo(domainFarmer.idType)
        assertThat(result.idNumber).isEqualTo(domainFarmer.idNumber)
        assertThat(result.issueDate).isEqualTo(domainFarmer.issueDate)
        assertThat(result.expiryDate).isEqualTo(domainFarmer.expiryDate)
        assertThat(result.idImage).isEqualTo(domainFarmer.idImage)
        assertThat(result.passportPhoto).isEqualTo(domainFarmer.passportPhoto)
        assertThat(result.fingerprint).isEqualTo(domainFarmer.fingerprint)
    }
}