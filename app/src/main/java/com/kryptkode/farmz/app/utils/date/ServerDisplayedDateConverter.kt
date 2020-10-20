package com.kryptkode.farmz.app.utils.date

import java.util.*
import javax.inject.Inject

class ServerDisplayedDateConverter @Inject constructor(
    private val serverDateFormatter: ServerDateFormatter,
    private val displayedDateFormatter: DisplayedDateFormatter
) {

    fun convertDisplayedDateToServerDate(displayedDate: String): String {
        val dateDisplayed = displayedDateFormatter.parseDisplayedDate(displayedDate)
        return serverDateFormatter.formatToServerDate(dateDisplayed)
    }

    fun convertServerDateToDisplayedDate(serverDate: String): String {
        val dateFromServer = serverDateFormatter.parseServerDate(serverDate)
        return formatToDisplayedDate(dateFromServer)
    }

    fun formatToDisplayedDate(date: Date?): String {
        return displayedDateFormatter.formatToDisplayedDate(date)
    }
}