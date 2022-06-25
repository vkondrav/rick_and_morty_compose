package com.vkondrav.ram.app.design

import com.vkondrav.ram.datastore.DataStoreManager
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

class ThemeController(
    private val dataStore: DataStoreManager,
    private val dispatcher: CoroutineDispatcher,
) : CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = dispatcher + exceptionHandler

    fun isThemeDark(initialSystemSetting: Boolean): Flow<Boolean> {
        setInitialPreference(initialSystemSetting)
        return dataStore.isDarkTheme()
    }

    fun toggleTheme() {
        launch {
            dataStore.toggleDarkTheme()
        }
    }

    private fun setInitialPreference(initialSetting: Boolean) {
        launch {
            dataStore.setInitialDarkTheme(initialSetting)
        }
    }

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Timber.e(throwable)
    }
}
