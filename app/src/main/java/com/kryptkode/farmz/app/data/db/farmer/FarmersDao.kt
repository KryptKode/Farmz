package com.kryptkode.farmz.app.data.db.farmer

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Query
import com.kryptkode.farmz.app.data.db.base.BaseDao

@Dao
abstract class FarmersDao : BaseDao<DbFarmer> {
    @Query("SELECT * FROM farmers")
    abstract fun getFarmers(): DataSource.Factory<Int, DbFarmer>
}