package com.kryptkode.farmz.app.utils.date

import timber.log.Timber
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class ServerDateFormatter @Inject constructor() {
    private val serverDateFormatter = SimpleDateFormat(PATTERN, Locale.ENGLISH)

    private val serverDateParseFormatter = SimpleDateFormat(PATTERN, Locale.ENGLISH).apply {
        timeZone = TimeZone.getTimeZone("UTC")
    }

    fun parseServerDate(date: String): Date? {
        return try {
            serverDateParseFormatter.parse(date)
        } catch (ex: ParseException) {
            Timber.w(ex, "Error parsing date")
            return null
        }
    }

    fun formatToServerDate(date: Date?): String {
        return if (date == null) {
            ""
        } else {
            serverDateFormatter.format(date)
        }
    }

    companion object {
        const val PATTERN = "yyyy-MM-dd"
    }
}