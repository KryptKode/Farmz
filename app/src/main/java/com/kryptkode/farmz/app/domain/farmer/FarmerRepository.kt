package com.kryptkode.farmz.app.domain.farmer

import androidx.annotation.VisibleForTesting
import androidx.paging.PagingData
import com.kryptkode.farmz.app.domain.Farmer
import kotlinx.coroutines.flow.Flow

interface FarmerRepository {
    fun getFarmers(): Flow<PagingData<Farmer>>

    companion object {
        @VisibleForTesting
        const val NETWORK_PAGE_SIZE = 50
    }
}