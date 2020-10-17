package com.kryptkode.farmz.app.data.keyvaluestore

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

const val DATA_STORE_NAME = "Farmz"

class KeyValueStoreImpl @Inject constructor(context: Context) : KeyValueStore {

    private val dataStore: DataStore<Preferences> = context.createDataStore(name = DATA_STORE_NAME)

    override suspend fun setLoggedIn(value: Boolean) {
        dataStore.edit {
            it[IS_LOGGED_IN_KEY] = value
        }
    }

    override val isLoggedIn: Flow<Boolean>
        get() =
            dataStore.data
                .catch(emptyPreferenceOnErrorAction)
                .map { it[IS_LOGGED_IN_KEY] ?: false }

    companion object {
        private val IS_LOGGED_IN_KEY = preferencesKey<Boolean>("IS_LOGGED_IN_KEY")

        private val emptyPreferenceOnErrorAction: suspend FlowCollector<Preferences>.(cause: Throwable) -> Unit =
            { exception ->
                // dataStore.data throws an IOException when an error is encountered when reading data
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
    }
}