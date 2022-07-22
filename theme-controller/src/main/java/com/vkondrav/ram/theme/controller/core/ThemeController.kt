package com.vkondrav.ram.theme.controller.core

import androidx.datastore.preferences.core.booleanPreferencesKey
import com.vkondrav.ram.datastore.DataStoreManager
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import timber.log.Timber

class ThemeController(
    private val dataStore: DataStoreManager,
    private val dispatcher: CoroutineDispatcher,
) : CoroutineScope {

    private val isDarkThemeKey = booleanPreferencesKey("is_dark_theme")

    override val coroutineContext: CoroutineContext
        get() = dispatcher + exceptionHandler

    fun isThemeDark(initialSystemSetting: Boolean): Flow<Boolean> {
        setInitialPreference(initialSystemSetting)
        return dataStore.data(isDarkThemeKey)
    }

    fun toggleTheme() {
        launch {
            dataStore.toggle(isDarkThemeKey)
        }
    }

    private fun setInitialPreference(initialSetting: Boolean) {
        launch {
            dataStore.initial(isDarkThemeKey, initialSetting)
        }
    }

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Timber.e(throwable)
    }
}
