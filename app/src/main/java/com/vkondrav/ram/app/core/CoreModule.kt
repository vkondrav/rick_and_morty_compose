package com.vkondrav.ram.app.core

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.vkondrav.ram.app.common.snackbar.SnackbarController
import com.vkondrav.ram.app.common.drawer.DrawerController
import com.vkondrav.ram.app.common.navigation.Navigator
import com.vkondrav.ram.app.design.ThemeController
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val coreModule = module {
    factory<Context> {
        androidApplication()
    }
    factory {
        Dispatchers.Default
    }
    single {
        Navigator()
    }
    single {
        SnackbarController()
    }
    single {
        DrawerController()
    }
    factory {
        get<Context>().dataStore
    }
    single {
        ThemeController(
            dataStore = get(),
            dispatcher = get(),
        )
    }
}

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
