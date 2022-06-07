package com.vkondrav.playground.app.screen.drawer.di

import com.vkondrav.playground.app.screen.drawer.usecase.DrawerMenuSource
import com.vkondrav.playground.app.screen.drawer.usecase.NavigateToRouteUseCase
import com.vkondrav.playground.app.screen.drawer.viewmodel.DrawerViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val drawerModule =  module {
    factory {
        NavigateToRouteUseCase(
            appState = get(),
        )
    }
    factory {
        DrawerMenuSource(
            navigateToRouteUseCase = get(),
        )
    }
    viewModel {
        DrawerViewModel(
            drawerMenuSource = get(),
            dispatcher = get(),
        )
    }
}
