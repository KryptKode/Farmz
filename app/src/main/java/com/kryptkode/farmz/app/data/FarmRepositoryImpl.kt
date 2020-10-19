package com.kryptkode.farmz.app.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.kryptkode.farmz.app.data.db.AppDatabase
import com.kryptkode.farmz.app.data.db.farm.mapper.FarmDbMapper
import com.kryptkode.farmz.app.data.state.DataState
import com.kryptkode.farmz.app.dispatcher.AppDispatchers
import com.kryptkode.farmz.app.domain.farm.Farm
import com.kryptkode.farmz.app.domain.farm.FarmRepository
import com.kryptkode.farmz.app.domain.farm.FarmRepository.Companion.PAGE_SIZE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FarmRepositoryImpl @Inject constructor(
    private val dispatchers: AppDispatchers,
    private val appDatabase: AppDatabase,
    private val farmDbMapper: FarmDbMapper
) : FarmRepository {

    override fun getFarms(): Flow<PagingData<Farm>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = { appDatabase.farmsDao().getFarms() }
        ).flow.map {
            it.map {
                farmDbMapper.mapDbToDomain(it)
            }
        }
    }

    override fun getFarmsByFarmer(farmerId: String): Flow<PagingData<Farm>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = { appDatabase.farmsDao().getFarmsByFarmerIdAsFlow(farmerId) }
        ).flow.map {
            it.map {
                farmDbMapper.mapDbToDomain(it)
            }
        }
    }

    override fun getFarmById(id: Int): Flow<Farm> {
        return appDatabase.farmsDao().getFarmByIdAsFlow(id)
            .map {
                farmDbMapper.mapDbToDomain(it)
            }
    }

    override suspend fun updateFarmer(farm: Farm): DataState<Unit> {
        return withContext(dispatchers.io()) {
            val result = appDatabase.farmsDao().update(
                farmDbMapper.mapDomainToDb(farm)
            )
            if (result > 0) {
                DataState.Success(Unit)
            } else {
                DataState.Error("Failed to update")
            }
        }
    }

    override suspend fun addFarm(farm: Farm): DataState<Unit> {
        return withContext(dispatchers.io()) {
            appDatabase.farmsDao().insert(
                farmDbMapper.mapDomainToDb(farm)
            )
            DataState.Success(Unit)
        }
    }
}