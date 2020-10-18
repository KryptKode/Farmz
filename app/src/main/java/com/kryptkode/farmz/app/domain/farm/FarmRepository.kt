package com.kryptkode.farmz.app.domain.farm

import androidx.annotation.VisibleForTesting
import androidx.paging.PagingData
import com.kryptkode.farmz.app.data.state.DataState
import kotlinx.coroutines.flow.Flow

interface FarmRepository {
    fun getFarms(): Flow<PagingData<Farm>>
    fun getFarmsByFarmer(farmerId: String):  Flow<PagingData<Farm>>
    fun getFarmById(id: Int): Flow<Farm>
    suspend fun updateFarmer(farm: Farm): DataState<Unit>

    companion object {
        @VisibleForTesting
        const val PAGE_SIZE = 50
    }
}