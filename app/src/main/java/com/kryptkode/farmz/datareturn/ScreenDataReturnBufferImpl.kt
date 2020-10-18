package com.kryptkode.farmz.datareturn

class ScreenDataReturnBufferImpl : ScreenDataReturnBuffer {
    private val buffer = mutableMapOf<String, Any?>()

    override fun <T> getValue(key: String): T? {
        return if (hasDataForToken(key)) {
            castOrNull(buffer[key])
        } else {
            null
        }
    }

    override fun removeValue(key: String) {
        buffer.remove(key)
    }

    override fun hasDataForToken(key: String): Boolean {
        return buffer.containsKey(key)
    }

    override fun <T> putValue(key: String, value: T?) {
        buffer[key] = value
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T> castOrNull(value: Any?): T? {
        return try {
            value as T
        } catch (ex: ClassCastException) {
            null
        }
    }
}