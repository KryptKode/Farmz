package com.kryptkode.farmz.app.data

import com.kryptkode.farmz.app.data.db.AppDatabase
import com.kryptkode.farmz.app.data.db.farmer.FarmerDbMapper
import com.kryptkode.farmz.app.domain.farmer.FarmerRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class FarmerRepositoryTest {

    private lateinit var farmerRepository: FarmerRepository

    @MockK
    lateinit var appDatabase: AppDatabase

    @MockK
    lateinit var farmerDbMapper: FarmerDbMapper

    @MockK
    lateinit var remoteMediator: FarmerRemoteMediator

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        farmerRepository = FarmerRepositoryImpl(remoteMediator, appDatabase, farmerDbMapper)
        coEvery {
            remoteMediator.initialize()
        }
    }

    @Test
    fun `getFarmers calls db`() = runBlocking {
        farmerRepository.getFarmers()
            .single()
        coVerify { appDatabase.farmersDao().getFarmers()}
    }


}