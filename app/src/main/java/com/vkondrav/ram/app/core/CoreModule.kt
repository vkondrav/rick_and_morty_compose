package com.vkondrav.ram.app.core

import android.content.Context
import com.vkondrav.ram.app.common.snackbar.SnackbarController
import com.vkondrav.ram.app.common.drawer.DrawerController
import com.vkondrav.ram.app.common.navigation.Navigator
import com.vkondrav.ram.app.design.ThemeState
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
    single {
        ThemeState()
    }
}
