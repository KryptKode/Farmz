package com.kryptkode.farmz.app.utils

import android.content.Context
import android.os.Build

/**
 * Created by kryptkode on 11/18/2019.
 */
class VersionUtil constructor(
    context: Context
) {

    private val info = context.packageManager.getPackageInfo(context.packageName, 0)

    fun getAppVersionName(): String? {
        return info.versionName
    }

    @Suppress("DEPRECATION")
    fun getAppVersionCode(): Long {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            info.longVersionCode
        } else {
            info.versionCode.toLong()
        }
    }
}