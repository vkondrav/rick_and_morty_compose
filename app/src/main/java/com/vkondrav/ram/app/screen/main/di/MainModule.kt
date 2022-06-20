package com.vkondrav.ram.app.screen.main.di

import com.vkondrav.ram.app.common.collapsable_drawer.state.SnackbarMessageStateHolder
import com.vkondrav.ram.app.common.drawer.DrawerController
import com.vkondrav.ram.app.common.navigation.Navigator
import com.vkondrav.ram.app.design.ThemeState
import com.vkondrav.ram.app.screen.main.MainActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    single {
        Navigator()
    }
    single {
        SnackbarMessageStateHolder()
    }
    single {
        ThemeState()
    }
    single {
        DrawerController()
    }
    viewModel {
        MainActivityViewModel(
            themeState = get(),
            drawerStateHolder = get(),
            navigator = get(),
            snackbarMessageStateHolder = get(),
            dispatcher = get(),
        )
    }
}
