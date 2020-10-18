package com.kryptkode.farmz.app.utils

import android.os.Build
import android.telephony.PhoneNumberUtils
import javax.inject.Inject

class PhoneNumberSanitizer @Inject constructor() {

    fun addLeadingZeroIfNeeded(phoneNumber: String): String {
        return if (phoneNumber.startsWith("0") && phoneNumber.length == 11) {
            phoneNumber
        } else {
            "0$phoneNumber"
        }
    }


    fun removeLeadingZeroIfNeeded(phoneNumber: String): String {
        return if (phoneNumber.startsWith("0") && phoneNumber.length == 11) {
            phoneNumber.substring(1)
        } else {
            phoneNumber
        }
    }

    fun formatWithCountryCode(phoneNumber: String): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            PhoneNumberUtils.formatNumber("$COUNTRY_CODE${removeLeadingZeroIfNeeded(phoneNumber)}", COUNTRY_ISO_CODE)
        } else {
            @Suppress("DEPRECATION")
            PhoneNumberUtils.formatNumber("$COUNTRY_CODE${removeLeadingZeroIfNeeded(phoneNumber)}")
        }
    }

    companion object {
        private const val COUNTRY_CODE = "+234"
        private const val COUNTRY_ISO_CODE = "NG"
    }
}

fun String.formatPhoneNumber(): String {
    if (this.isEmpty()) return ""

    val result = StringBuilder()
    for (i in this.indices) {
        if (i % 3 == 0 && i != 0) {
            result.append(" ")
        }
        result.append(this[i])
    }
    return result.toString()
}