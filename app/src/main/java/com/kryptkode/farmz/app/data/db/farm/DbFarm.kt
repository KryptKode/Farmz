package com.kryptkode.farmz.app.data.db.farm

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kryptkode.farmz.app.data.db.farm.DbFarm.Companion.TABLE_NAME
import java.util.*

@Entity(tableName = TABLE_NAME)
data class DbFarm(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val farmerId: String,
    val name: String,
    val location: String,
    val dbFarmCoordinates: List<DbFarmLocation>,
    val dateLastUpdated: Date,
    val dateCreated: Date
) {
    companion object {
        const val TABLE_NAME = "farms"
    }
}