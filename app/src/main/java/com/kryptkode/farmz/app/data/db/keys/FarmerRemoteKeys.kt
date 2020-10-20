package com.kryptkode.farmz.app.data.db.keys

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kryptkode.farmz.app.data.db.keys.FarmerRemoteKeys.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class FarmerRemoteKeys(
        @PrimaryKey val farmerId: String,
        val prevKey: Int?,
        val nextKey: Int?
) {
    companion object {
        const val TABLE_NAME = "farmer_remote_keys"
    }
}
