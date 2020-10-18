package com.kryptkode.farmz.app.data.db.farm

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import com.kryptkode.farmz.app.data.db.base.BaseDao
import kotlinx.coroutines.flow.Flow

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

    @Query("DELETE FROM farms")
    abstract suspend fun clearFarmers()
}