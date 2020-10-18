package com.kryptkode.farmz.app.data.db.farm

import androidx.room.TypeConverter
import com.squareup.moshi.Moshi

class DbFarmCoordinatesConverter {

    companion object {

        private val moshi: Moshi = Moshi.Builder().build()

        @TypeConverter
        @JvmStatic
        fun convertCoordinatesToString(coordinates: DbFarmCoordinates?): String? {
            return moshi.adapter(DbFarmCoordinates::class.java).toJson(coordinates)
        }

        @TypeConverter
        @JvmStatic
        fun convertStringToCoordinates(coordinates: String): DbFarmCoordinates? {
            return moshi.adapter(DbFarmCoordinates::class.java).fromJson(coordinates)
        }

        @TypeConverter
        @JvmStatic
        fun convertStringToFarmLocation(coordinates: String): DbLocation? {
            return moshi.adapter(DbLocation::class.java).fromJson(coordinates)
        }

        @TypeConverter
        @JvmStatic
        fun convertFarmLocationToString(coordinates: DbLocation): String {
            return moshi.adapter(DbLocation::class.java).toJson(coordinates)
        }
    }
}