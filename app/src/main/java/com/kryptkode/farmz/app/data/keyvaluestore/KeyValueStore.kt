package com.kryptkode.farmz.app.data.keyvaluestore

import kotlinx.coroutines.flow.Flow


interface KeyValueStore {

    suspend fun setLoggedIn(value: Boolean)
    val isLoggedIn: Flow<Boolean>
}