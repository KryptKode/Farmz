package com.kryptkode.farmz.app.data.auth

import androidx.annotation.VisibleForTesting
import com.kryptkode.farmz.app.data.keyvaluestore.KeyValueStore
import com.kryptkode.farmz.app.data.state.DataState
import com.kryptkode.farmz.app.dispatcher.AppDispatchers
import com.kryptkode.farmz.app.domain.AuthRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class AuthRepositoryImpl(
    private val dispatchers: AppDispatchers,
    keyValueStore: KeyValueStore
) : AuthRepository, KeyValueStore by keyValueStore {

    override suspend fun login(email: String, password: String): DataState<Unit> {
        return withContext(dispatchers.io()) {
            delay(DELAY_MILLIS) //simulate delay
            if (email in testLoginDetails.keys) {
                if (password == testLoginDetails[email]) {
                    setLoggedIn(true)
                    println("Logging in")
                    DataState.Success(Unit)
                } else {
                    println("Error in")
                    DataState.Error("Password incorrect")
                }
            } else {
                DataState.Error("User not found")
            }
        }
    }

    companion object {
        private const val DELAY_MILLIS = 2_000L

        @VisibleForTesting
        val testLoginDetails =
            mapOf("test@tellerium.io" to "password", "a@b.c" to "123456")
    }
}