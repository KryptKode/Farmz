package com.kryptkode.farmz.app.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.kryptkode.farmz.app.data.db.AppDatabase
import com.kryptkode.farmz.app.data.db.farmer.DbFarmer
import com.kryptkode.farmz.app.data.db.farmer.FarmerDbMapper
import com.kryptkode.farmz.app.data.db.keys.FarmerRemoteKeys
import com.kryptkode.farmz.app.data.network.mapper.FarmerApiMapper
import com.kryptkode.farmz.app.data.network.service.FarmersService
import retrofit2.HttpException
import java.io.IOException
import java.io.InvalidObjectException
import javax.inject.Inject

private const val STARTING_PAGE_INDEX = 1

@OptIn(ExperimentalPagingApi::class)
class FarmerRemoteMediator @Inject constructor(
    private val service: FarmersService,
    private val repoDatabase: AppDatabase,
    private val farmerApiMapper: FarmerApiMapper,
    private val farmerDbMapper: FarmerDbMapper
) : RemoteMediator<Int, DbFarmer>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, DbFarmer>
    ): MediatorResult {

        val page = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: STARTING_PAGE_INDEX
            }
            LoadType.PREPEND -> {
                // The LoadType is PREPEND so some data was loaded before,
                // so we should have been able to get remote keys
                // If the remoteKeys are null, then we're an invalid state and we have a bug
                val remoteKeys = getRemoteKeyForFirstItem(state)
                    ?: throw InvalidObjectException("Remote key and the prevKey should not be null")
                // If the previous key is null, then we can't request more data
                remoteKeys.prevKey
                    ?: return MediatorResult.Success(
                        endOfPaginationReached = true
                    )
            }
            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                if (remoteKeys?.nextKey == null) {
                    throw InvalidObjectException("Remote key should not be null for $loadType")
                }

                remoteKeys.nextKey
            }

        }
        try {

            val apiResponse = service.getFarmers(page)
            if (apiResponse.status) {
                val data = apiResponse.data
                val endOfPaginationReached = data.totalRecords == data.farmers.size
                repoDatabase.withTransaction {
                    // clear all tables in the database
                    if (loadType == LoadType.REFRESH) {
                        repoDatabase.farmersRemoteKeysDao().clearRemoteKeys()
                        repoDatabase.farmersDao().clearFarmers()
                    }

                    val prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1
                    val nextKey = if (endOfPaginationReached) null else page + 1
                    val keys = data.farmers.map {
                        FarmerRemoteKeys(farmerId = it.id, prevKey = prevKey, nextKey = nextKey)
                    }
                    repoDatabase.farmersRemoteKeysDao().insert(keys)
                    repoDatabase.farmersDao().insert(data.farmers.map {
                        farmerDbMapper.mapDomainToDb(farmerApiMapper.mapRemoteToDomain(it))
                    })
                }
                return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
            }
            return MediatorResult.Error(IOException("Error from server"))
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, DbFarmer>): FarmerRemoteKeys? {
        // Get the last page that was retrieved, that contained items.
        // From that last page, get the last item
        return state.pages.lastOrNull() { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { farmer ->
                // Get the remote keys of the last item retrieved
                repoDatabase.farmersRemoteKeysDao().getRemoteKeysById(farmer.id)
            }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, DbFarmer>): FarmerRemoteKeys? {
        // Get the first page that was retrieved, that contained items.
        // From that first page, get the first item
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { farmer ->
                // Get the remote keys of the first items retrieved
                repoDatabase.farmersRemoteKeysDao().getRemoteKeysById(farmer.id)
            }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, DbFarmer>
    ): FarmerRemoteKeys? {
        // The paging library is trying to load data after the anchor position
        // Get the item closest to the anchor position
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                repoDatabase.farmersRemoteKeysDao().getRemoteKeysById(id)
            }
        }
    }

}