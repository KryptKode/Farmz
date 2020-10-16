package com.kryptkode.farmz.app.data.db.farmer

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import com.kryptkode.farmz.app.data.db.AppDatabase
import com.kryptkode.farmz.app.data.mock.FarmerDataFactory
import com.kryptkode.farmz.utils.MockDataFactory.randomString
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class FarmersDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var appDatabase: AppDatabase
    private lateinit var dao: FarmersDao

    private val farmerDataFactory = FarmerDataFactory()

    @Before
    fun initDb() {
        appDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries()
            .build()

        dao = appDatabase.farmersDao()
    }


    @Test
    fun insert_oneFarmer_addedToDb() {
        val farmer = farmerDataFactory.createMockDbFarmer()
        dao.insert(farmer)

        val result = dao.getFarmersAsList()
        assertThat(result.size).isEqualTo(1)
    }

    @Test
    fun insert_listOfFarmers_addedToDb() {
        val farmers = listOf(
            farmerDataFactory.createMockDbFarmer(),
            farmerDataFactory.createMockDbFarmer(),
            farmerDataFactory.createMockDbFarmer()
        )
        dao.insert(farmers)

        val result = dao.getFarmersAsList()
        assertThat(result).isEqualTo(farmers)
    }

    @Test
    fun update_oneFarmer_returnsUpdatedData() {
        val farmer = farmerDataFactory.createMockDbFarmer()
        dao.insert(farmer)


        val updatedFirstName = randomString()
        val updatedFarmer = farmer.copy(firstName = updatedFirstName)

        dao.update(updatedFarmer)

        val result = dao.getFarmerById(farmer.id)
        assertThat(result).isEqualTo(updatedFarmer)
    }

    @Test
    fun update_listOfFarmers_returnsUpdatedData() {
        val farmers = listOf(
            farmerDataFactory.createMockDbFarmer(),
            farmerDataFactory.createMockDbFarmer(),
            farmerDataFactory.createMockDbFarmer()
        )
        dao.insert(farmers)

        val updatedLastName = randomString()

        val updatedFarmers = farmers.take(2).map { it.copy(surname = updatedLastName) }
        dao.update(updatedFarmers)

        updatedFarmers.onEach { farmer ->
            val result = dao.getFarmerById(farmer.id)
            assertThat(result.surname).isEqualTo(updatedLastName)
        }
    }

    @Test
    fun getFarmers_ReturnsData() {
        val farmers = listOf(
            farmerDataFactory.createMockDbFarmer(),
            farmerDataFactory.createMockDbFarmer(),
            farmerDataFactory.createMockDbFarmer()
        )
        dao.insert(farmers)

        val result = dao.getFarmersAsList()
        assertThat(result).isEqualTo(farmers)
    }


    @After
    fun cleanUp() {
        appDatabase.close()
    }
}
