package com.kryptkode.farmz.app.data.db.farm

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import com.kryptkode.farmz.app.data.db.base.BaseDao
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
abstract class FarmDao : BaseDao<DbFarm> {

    @Query("SELECT * FROM farms")
    abstract fun getFarms(): PagingSource<Int, DbFarm>

    @Query("SELECT * FROM farms WHERE id=:id")
    abstract fun getFarmByIdAsFlow(id: Int): Flow<DbFarm>

    @Query("SELECT * FROM farms WHERE farmerId=:farmerId")
    abstract fun getFarmsByFarmerIdAsFlow(farmerId: String): PagingSource<Int, DbFarm>

    @Query("SELECT * FROM farms WHERE farmerId=:farmerId")
    abstract fun getFarmsByFarmerIdAsList(farmerId: String): List<DbFarm>

    @Query("SELECT * FROM farms WHERE id=:id")
    abstract fun getFarmById(id: Int): DbFarm

    @Query("SELECT * FROM farms")
    abstract fun getFarmsAsList(): List<DbFarm>


    @Query("SELECT COUNT(*) FROM farms")
    abstract fun countFarms(): Flow<Int>

    @Query("SELECT COUNT(DISTINCT farmerId) from farms")
    abstract fun countCapturedFarmers(): Flow<Int>

    @Query("SELECT * FROM farms ORDER BY dateLastUpdated LIMIT :limit")
    abstract fun getLastUpdatedFarms(limit: Int): PagingSource<Int, DbFarm>


    @Query("SELECT dateLastUpdated FROM farms ORDER BY dateLastUpdated LIMIT 1")
    abstract fun getLastUpdatedDate(): Flow<Date>

    @Query("DELETE FROM farms")
    abstract suspend fun clearFarmers()
}