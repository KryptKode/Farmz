package com.kryptkode.farmz.app.data.db

import androidx.room.TypeConverter
import java.text.SimpleDateFormat
import java.util.*

class DateConverter {
    companion object {
        @JvmStatic
        val formatter = SimpleDateFormat("yyyyMMddHHmmss", Locale.ENGLISH)

        @TypeConverter
        @JvmStatic
        fun toDate(text: String): Date = formatter.parse(text)

        @TypeConverter
        @JvmStatic
        fun toText(date: Date): String = formatter.format(date)
    }
}