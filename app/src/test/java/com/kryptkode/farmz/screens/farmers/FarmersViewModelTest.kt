package com.kryptkode.farmz.screens.farmers

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingData
import com.kryptkode.farmz.app.domain.Farmer
import com.kryptkode.farmz.app.domain.farmer.FarmerRepository
import com.kryptkode.farmz.base.MainCoroutineRule
import com.kryptkode.farmz.screens.farmers.model.FarmerViewMapper
import com.kryptkode.farmz.utils.FarmerDataFactory
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class FarmersViewModelTest {
    // Executes tasks in the Architecture Components in the same thread
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    // Overrides Dispatchers.Main used in Coroutines
    @get:Rule
    var coroutineRule = MainCoroutineRule()

    private val farmerRepository: FarmerRepository = mockk()
    private val farmerViewMapper: FarmerViewMapper = mockk()
    private val farmerFactory = FarmerDataFactory()

    private lateinit var viewModel: FarmersViewModel

    private var testFarmerViewList = listOf(
        farmerFactory.createMockFarmerView(),
        farmerFactory.createMockFarmerView()
    )

    @Before
    fun setUp() {
        viewModel = FarmersViewModel(farmerRepository, farmerViewMapper)
        stubFarmerRepository()
        stubFarmerViewMapper()
    }

    @Test
    fun `loadFarmers calls repository`() {

        viewModel.loadFarmers()

        verify {
            farmerRepository.getFarmers()
        }
    }


    private fun stubFarmerRepository() {
        every {
            farmerRepository.getFarmers()
        } returns flowOf(PagingData.from(testFarmerViewList.map { farmerView ->
            Farmer(
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
        }))
    }

    private fun stubFarmerViewMapper() {
        every {
            farmerViewMapper.mapDomainToView(any())
        } returns farmerFactory.createMockFarmerView()
    }
}