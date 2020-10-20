package com.kryptkode.farmz.app.data.network.mapper

import com.google.common.truth.Truth.assertThat
import com.kryptkode.farmz.utils.FarmerDataFactory
import org.junit.Before
import org.junit.Test

class FarmerApiMapperTest {

    private lateinit var farmerApiMapper: FarmerApiMapper

    private val farmerDataFactory = FarmerDataFactory()

    @Before
    fun setUp() {
        farmerApiMapper = FarmerApiMapperImpl()
    }

    @Test
    fun `mapDbToDomain maps all fields correctly`() {
        val farmerRemote = farmerDataFactory.createMockRemoteFarmer()
        val result = farmerApiMapper.mapRemoteToDomain(farmerRemote)

        assertThat(result.id).isEqualTo(farmerRemote.id)
        assertThat(result.registrationNumber).isEqualTo(farmerRemote.registrationNumber)
        assertThat(result.bvn).isEqualTo(farmerRemote.bvn)
        assertThat(result.firstName).isEqualTo(farmerRemote.firstName)
        assertThat(result.middleName).isEqualTo(farmerRemote.middleName)
        assertThat(result.surname).isEqualTo(farmerRemote.surname)
        assertThat(result.dateOfBirth).isEqualTo(farmerRemote.dateOfBirth)
        assertThat(result.gender).isEqualTo(farmerRemote.gender)
        assertThat(result.nationality).isEqualTo(farmerRemote.nationality)
        assertThat(result.occupation).isEqualTo(farmerRemote.occupation)
        assertThat(result.maritalStatus).isEqualTo(farmerRemote.maritalStatus)
        assertThat(result.spouseName).isEqualTo(farmerRemote.spouseName)
        assertThat(result.address).isEqualTo(farmerRemote.address)
        assertThat(result.city).isEqualTo(farmerRemote.city)
        assertThat(result.lga).isEqualTo(farmerRemote.lga)
        assertThat(result.state).isEqualTo(farmerRemote.state)
        assertThat(result.mobileNumber).isEqualTo(farmerRemote.mobileNumber)
        assertThat(result.emailAddress).isEqualTo(farmerRemote.emailAddress)
        assertThat(result.idType).isEqualTo(farmerRemote.idType)
        assertThat(result.idNumber).isEqualTo(farmerRemote.idNumber)
        assertThat(result.issueDate).isEqualTo(farmerRemote.issueDate)
        assertThat(result.expiryDate).isEqualTo(farmerRemote.expiryDate)
        assertThat(result.idImage).isEqualTo(farmerRemote.idImage)
        assertThat(result.passportPhoto).isEqualTo(farmerRemote.passportPhoto)
        assertThat(result.fingerprint).isEqualTo(farmerRemote.fingerprint)
    }
}