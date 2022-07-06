package com.vkondrav.ram.app.core

import android.content.Context
import com.vkondrav.ram.apollo.SERVER_URL
import com.vkondrav.ram.app.common.drawer.DrawerController
import com.vkondrav.ram.snackbar.SnackbarController
import com.vkondrav.ram.app.design.ThemeController
import com.vkondrav.ram.datastore.DATASTORE_NAME
import com.vkondrav.ram.room.DATABASE_NAME
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
        com.vkondrav.ram.snackbar.SnackbarController()
    }
    single {
        DrawerController()
    }
    single {
        ThemeController(
            dataStore = get(),
            dispatcher = get(),
        )
    }
    factory(SERVER_URL) { "https://rickandmortyapi.com/graphql" }
    factory(DATABASE_NAME) { "ram_database" }
    factory(DATASTORE_NAME) { "settings" }
}

