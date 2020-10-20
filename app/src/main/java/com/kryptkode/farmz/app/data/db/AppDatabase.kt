package com.kryptkode.farmz.app.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kryptkode.farmz.app.data.db.farm.DbFarm
import com.kryptkode.farmz.app.data.db.farm.DbFarmCoordinatesConverter
import com.kryptkode.farmz.app.data.db.farm.FarmDao
import com.kryptkode.farmz.app.data.db.farmer.DbFarmer
import com.kryptkode.farmz.app.data.db.farmer.FarmersDao
import com.kryptkode.farmz.app.data.db.keys.FarmerRemoteKeys
import com.kryptkode.farmz.app.data.db.keys.FarmerRemoteKeysDao

/**
 * Database schema that holds the list of repos.
 */
@Database(
    entities = [DbFarmer::class, FarmerRemoteKeys::class, DbFarm::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(
    DbFarmCoordinatesConverter::class,
    DateConverter::class
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun farmersDao(): FarmersDao
    abstract fun farmsDao(): FarmDao
    abstract fun farmersRemoteKeysDao(): FarmerRemoteKeysDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java, "farmz.db"
            )
                .build()
    }

}
