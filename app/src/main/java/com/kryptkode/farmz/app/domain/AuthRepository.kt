package com.kryptkode.farmz.app.domain

import com.kryptkode.farmz.app.data.state.DataState
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun login(email:String, password:String): DataState<Unit>
    val isLoggedIn: Flow<Boolean>
}