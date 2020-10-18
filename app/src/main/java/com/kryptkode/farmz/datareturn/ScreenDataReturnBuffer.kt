package com.kryptkode.farmz.datareturn

interface ScreenDataReturnBuffer {
    fun hasDataForToken(key: String): Boolean
    fun removeValue(key: String)
    fun <T> putValue(key: String, value: T?)
    fun <T> getValue(key: String): T?
}