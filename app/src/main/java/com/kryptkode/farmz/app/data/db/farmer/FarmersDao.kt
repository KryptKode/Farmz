package com.kryptkode.farmz.app.data.db.farmer

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import com.kryptkode.farmz.app.data.db.base.BaseDao
import kotlinx.coroutines.flow.Flow

@Dao
abstract class FarmersDao : BaseDao<DbFarmer> {
    @Query("SELECT * FROM farmers")
    abstract fun getFarmers(): PagingSource<Int, DbFarmer>

    @Query("SELECT * FROM farmers WHERE id=:id")
    abstract fun getFarmerByIdAsFlow(id:String): Flow<DbFarmer>

    @Query("SELECT * FROM farmers WHERE id=:id")
    abstract fun getFarmerById(id:String): DbFarmer

    @Query("SELECT * FROM farmers")
    abstract fun getFarmersAsList(): List<DbFarmer>

    @Query("DELETE FROM farmers")
    abstract suspend fun clearFarmers()
}