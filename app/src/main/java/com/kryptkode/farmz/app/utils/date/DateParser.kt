package com.kryptkode.farmz.app.utils.date

import java.util.*
import javax.inject.Inject

class DateParser @Inject constructor() {

    fun convertToDate(year: Int, month: Int, day: Int): Date {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, day)
        return calendar.time
    }

    fun convertToYearMonthDay(date: Date? = null): Triple<Int, Int, Int> {
        val calendar = Calendar.getInstance()
        date?.let {
            calendar.time = it
        }
        return Triple(
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
    }
}