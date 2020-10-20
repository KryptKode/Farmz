package com.kryptkode.farmz.app.data.db.farm

import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.lang.reflect.Type

class DbFarmCoordinatesConverter {

    companion object {

        private val moshi: Moshi = Moshi.Builder().build()
        private val farmLocationListType: Type = Types.newParameterizedType(
            List::class.java,
            DbFarmLocation::class.java
        )

        @TypeConverter
        @JvmStatic
        fun convertStringToFarmLocation(coordinates: String): List<DbFarmLocation>? {
            return moshi.adapter<List<DbFarmLocation>>(farmLocationListType).fromJson(coordinates)
        }

        @TypeConverter
        @JvmStatic
        fun convertFarmLocationToString(coordinates: List<DbFarmLocation>): String {
            return moshi.adapter<List<DbFarmLocation>>(farmLocationListType).toJson(coordinates)
        }
    }
}