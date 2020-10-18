package com.kryptkode.farmz.app.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.kryptkode.farmz.app.data.db.AppDatabase
import com.kryptkode.farmz.app.data.db.farmer.FarmerDbMapper
import com.kryptkode.farmz.app.domain.Farmer
import com.kryptkode.farmz.app.domain.farmer.FarmerRepository
import com.kryptkode.farmz.app.domain.farmer.FarmerRepository.Companion.NETWORK_PAGE_SIZE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FarmerRepositoryImpl(
    private val remoteMediator: FarmerRemoteMediator,
    private val appDatabase: AppDatabase,
    private val farmerDbMapper: FarmerDbMapper
) :
    FarmerRepository {

    override fun getFarmers(): Flow<PagingData<Farmer>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false,
                initialLoadSize = 100
            ),
            remoteMediator = remoteMediator,
            pagingSourceFactory = { appDatabase.farmersDao().getFarmers() }
        ).flow.map {
            it.map {
                farmerDbMapper.mapDbToDomain(it)
            }
        }
    }

    override fun getFarmerById(id: String): Flow<Farmer> {
        return appDatabase.farmersDao().getFarmerByIdAsFlow(id)
            .map {
                farmerDbMapper.mapDbToDomain(it)
            }
    }
}