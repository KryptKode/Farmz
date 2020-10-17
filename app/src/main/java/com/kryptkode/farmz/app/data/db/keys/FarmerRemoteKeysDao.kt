package com.kryptkode.farmz.app.data.db.keys

import androidx.room.Dao
import androidx.room.Query
import com.kryptkode.farmz.app.data.db.base.BaseDao

@Dao
abstract class FarmerRemoteKeysDao : BaseDao<FarmerRemoteKeys> {

    @Query("SELECT * FROM farmer_remote_keys WHERE farmerId = :farmerId")
    abstract suspend fun getRemoteKeysById(farmerId: String): FarmerRemoteKeys?

    @Query("DELETE FROM farmer_remote_keys")
    abstract suspend fun clearRemoteKeys()
}

