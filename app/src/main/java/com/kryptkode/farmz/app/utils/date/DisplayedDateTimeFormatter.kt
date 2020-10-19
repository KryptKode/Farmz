package com.kryptkode.farmz.app.utils.date

import timber.log.Timber
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class DisplayedDateTimeFormatter @Inject constructor() {
    private val displayedDateFormatter = SimpleDateFormat("MMM d, yyyy HH:mm a", Locale.ENGLISH)

    fun formatToDisplayedDate(date: Date?): String {
        return if (date == null) {
            displayedDateFormatter.format(Date())
        } else {
            displayedDateFormatter.format(date)
        }
    }

    fun parseDisplayedDate(date: String): Date? {
        return try {
            displayedDateFormatter.parse(date)
        } catch (ex: ParseException) {
            Timber.w(ex, "Error parsing date")
            return Date()
        }
    }
}