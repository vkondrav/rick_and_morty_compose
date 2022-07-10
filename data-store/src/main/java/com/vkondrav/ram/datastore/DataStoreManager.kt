package com.vkondrav.ram.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import com.vkondrav.ram.common.util.FlowWrapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import java.io.IOException

class DataStoreManager(
    context: Context,
    name: String,
    val wrapper: FlowWrapper,
) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = name)
    private val dataStore: DataStore<Preferences> by lazy { context.dataStore }

    suspend fun initial(key: Preferences.Key<Boolean>, initial: Boolean) {
        dataStore.edit {
            val value = it[key]
            if (value == null) {
                it[key] = initial
            }
        }
    }

    fun data(key: Preferences.Key<Boolean>): Flow<Boolean> {
        return wrapper(dataStore.data).catch { e ->
            if (e is IOException) {
                emit(emptyPreferences())
            } else {
                throw e
            }
        }.map { it[key] }.filterNotNull().distinctUntilChanged()
    }

    suspend fun toggle(key: Preferences.Key<Boolean>) {
        dataStore.edit {
            val value = it[key] ?: return@edit
            it[key] = !value
        }
    }
}
