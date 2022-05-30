package com.vkondrav.playground.app.screen.drawer.di

import com.vkondrav.playground.app.screen.drawer.usecase.DrawerMenuUseCase
import com.vkondrav.playground.app.screen.drawer.usecase.NavigateToRouteUseCase
import com.vkondrav.playground.app.screen.drawer.viewmodel.DrawerViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val drawerModule =  module {
    factory {
        DrawerMenuUseCase()
    }
    factory {
        NavigateToRouteUseCase(
            appState = get(),
        )
    }
    viewModel {
        DrawerViewModel(
            drawerMenuUseCase = get(),
            navigateToRouteUseCase = get(),
            dispatcher = get(),
        )
    }
}
