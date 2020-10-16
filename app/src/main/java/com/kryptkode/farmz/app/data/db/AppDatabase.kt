package com.kryptkode.farmz.app.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kryptkode.farmz.app.data.db.farmer.DbFarmer
import com.kryptkode.farmz.app.data.db.farmer.FarmersDao

/**
 * Database schema that holds the list of repos.
 */
@Database(
    entities = [DbFarmer::class],
    version = 1,
    exportSchema = true
)
@TypeConverters()
abstract class AppDatabase : RoomDatabase() {
    abstract fun farmersDao(): FarmersDao
}
