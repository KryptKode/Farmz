package com.kryptkode.farmz.screens.infodialog

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Info(
    var title: String,
    var message: String,
    var positiveButtonText: String? = null,
    var negativeButtonText: String? = null,
    var neutralButtonText: String? = null,
    var showPositiveButton: Boolean = true,
    var showNegativeButton: Boolean = true,
    var showNeutralButton: Boolean = false,
    var payload:String = ""
) : Parcelable