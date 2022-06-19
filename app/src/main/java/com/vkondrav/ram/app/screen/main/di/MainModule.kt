package com.vkondrav.ram.app.screen.main.di

import com.vkondrav.ram.app.common.collapsable_drawer.state.SnackbarMessageStateHolder
import com.vkondrav.ram.app.common.state.DrawerStateHolder
import com.vkondrav.ram.app.common.state.NavigationStateHolder
import com.vkondrav.ram.app.design.ThemeState
import com.vkondrav.ram.app.screen.main.MainActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    single {
        NavigationStateHolder()
    }
    single {
        SnackbarMessageStateHolder()
    }
    single {
        ThemeState()
    }
    single {
        DrawerStateHolder()
    }
    viewModel {
        MainActivityViewModel(
            themeState = get(),
            drawerStateHolder = get(),
            navigationStateHolder = get(),
            snackbarMessageStateHolder = get(),
            dispatcher = get(),
        )
    }
}
