package com.kryptkode.farmz.app.domain.farm

import androidx.annotation.VisibleForTesting
import androidx.paging.PagingData
import com.kryptkode.farmz.app.data.state.DataState
import kotlinx.coroutines.flow.Flow
import java.util.*

interface FarmRepository {
    fun getFarms(): Flow<PagingData<Farm>>
    fun getFarmsByFarmer(farmerId: String): Flow<PagingData<Farm>>
    fun getFarmById(id: Int): Flow<Farm>
    fun getFarmsCount(): Flow<Int>
    fun getCapturedFarmerCount(): Flow<Int>
    fun getLastUpdatedDate(): Flow<Date>
    fun getLastUpdatedFarms(limit:Int): Flow<PagingData<Farm>>
    suspend fun updateFarmer(farm: Farm): DataState<Unit>
    suspend fun addFarm(farm: Farm): DataState<Unit>

    companion object {
        @VisibleForTesting
        const val PAGE_SIZE = 50
    }
}