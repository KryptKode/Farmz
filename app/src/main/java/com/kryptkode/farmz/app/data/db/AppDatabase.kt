package com.kryptkode.farmz.app.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
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
