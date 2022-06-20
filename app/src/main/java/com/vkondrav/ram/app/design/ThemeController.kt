package com.vkondrav.ram.app.design

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.IOException
import kotlin.coroutines.CoroutineContext

class ThemeController(
    private val dataStore: DataStore<Preferences>,
    private val dispatcher: CoroutineDispatcher,
) : CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = dispatcher + exceptionHandler

    private val isDarkThemeKey = booleanPreferencesKey("is_dark_theme")

    fun isThemeDark(initialSystemSetting: Boolean): Flow<Boolean> {
        setInitialPreference(initialSystemSetting)
        return dataStore.data.catch { e ->
            if (e is IOException) {
                emit(emptyPreferences())
            } else {
                throw e
            }
        }.map { it[isDarkThemeKey] }.filterNotNull().distinctUntilChanged()
    }

    fun toggleTheme() {
        launch {
            dataStore.edit {
                val isDarkTheme = it[isDarkThemeKey] ?: return@edit
                it[isDarkThemeKey] = !isDarkTheme
            }
        }
    }

    private fun setInitialPreference(initialSetting: Boolean) {
        launch {
            dataStore.edit {
                val isDarkTheme = it[isDarkThemeKey]
                if (isDarkTheme == null) {
                    it[isDarkThemeKey] = initialSetting
                }
            }
        }
    }

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Timber.e(throwable)
    }
}
