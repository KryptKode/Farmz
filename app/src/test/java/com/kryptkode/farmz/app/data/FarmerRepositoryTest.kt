package com.kryptkode.farmz.app.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import androidx.paging.RemoteMediator
import com.kryptkode.farmz.app.data.db.AppDatabase
import com.kryptkode.farmz.app.data.db.farmer.DbFarmer
import com.kryptkode.farmz.app.data.db.farmer.FarmerDbMapper
import com.kryptkode.farmz.app.data.db.farmer.FarmersDao
import com.kryptkode.farmz.app.domain.farmer.FarmerRepository
import com.kryptkode.farmz.base.MainCoroutineRule
import com.kryptkode.farmz.utils.FarmerDataFactory
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Rule

@ExperimentalPagingApi
@ExperimentalCoroutinesApi
class FarmerRepositoryTest {

    // Overrides Dispatchers.Main used in Coroutines
    @get:Rule
    var coroutineRule = MainCoroutineRule()

    // Executes tasks in the Architecture Components in the same thread
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var farmerRepository: FarmerRepository

    @MockK
    lateinit var appDatabase: AppDatabase

    @MockK
    lateinit var farmerDbMapper: FarmerDbMapper

    @MockK
    lateinit var remoteMediator: FarmerRemoteMediator

    private val farmerFactory = FarmerDataFactory()

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        farmerRepository = FarmerRepositoryImpl(remoteMediator, appDatabase, farmerDbMapper)
        coEvery {
            remoteMediator.initialize()
        } returns RemoteMediator.InitializeAction.SKIP_INITIAL_REFRESH

        every {
            appDatabase.farmersDao()
        } returns StubbedFarmersDao()
    }

   /* @Test
    fun `getFarmers calls db`() = coroutineRule.runBlockingTest {
        farmerRepository.getFarmers()
            .single()
        coVerify { appDatabase.farmersDao().getFarmers() }
    }

    @Test
    fun `getFarmerById calls db with correct id`() = coroutineRule.runBlockingTest {
        val testId = randomString()
        farmerRepository.getFarmerById(testId)
            .single()
        coVerify { appDatabase.farmersDao().getFarmerById(testId) }
    }*/


    inner class StubbedFarmersDao : FarmersDao() {
        override suspend fun clearFarmers() {

        }

        override suspend fun delete(obj: DbFarmer): Int {
            return 0
        }

        override fun getFarmerById(id: String): DbFarmer {
            return farmerFactory.createMockDbFarmer()
        }

        override fun getFarmerByIdAsFlow(id: String): Flow<DbFarmer> {
            return flowOf(farmerFactory.createMockDbFarmer())
        }

        override fun getFarmers(): PagingSource<Int, DbFarmer> {
            return object : PagingSource<Int, DbFarmer>() {
                override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DbFarmer> {
                    return LoadResult.Page(listOf(), 0, 0)
                }
            }
        }

        override fun getFarmersAsList(): List<DbFarmer> {
            return listOf(farmerFactory.createMockDbFarmer())
        }

        override suspend fun insert(obj: List<DbFarmer>) {

        }

        override suspend fun insert(obj: DbFarmer) {

        }

        override suspend fun update(obj: DbFarmer): Int {
            return 0
        }

        override suspend fun update(obj: List<DbFarmer>): Int {
            return 0
        }
    }

}