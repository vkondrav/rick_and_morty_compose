package com.vkondrav.ram.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import java.io.IOException

class DataStoreManager(private val context: Context, name: String) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = name)

    private val isDarkThemeKey = booleanPreferencesKey("is_dark_theme")

    suspend fun setInitialDarkTheme(initialSetting: Boolean) {
        context.dataStore.edit {
            val isDarkTheme = it[isDarkThemeKey]
            if (isDarkTheme == null) {
                it[isDarkThemeKey] = initialSetting
            }
        }
    }

    suspend fun toggleDarkTheme() {
        context.dataStore.edit {
            val isDarkTheme = it[isDarkThemeKey] ?: return@edit
            it[isDarkThemeKey] = !isDarkTheme
        }
    }

    fun isDarkTheme(): Flow<Boolean> = context.dataStore.data.catch { e ->
        if (e is IOException) {
            emit(emptyPreferences())
        } else {
            throw e
        }
    }.map { it[isDarkThemeKey] }.filterNotNull().distinctUntilChanged()
}