package com.vkondrav.ram.app.screen.drawer.di

import com.vkondrav.ram.app.screen.drawer.usecase.DrawerMenuSource
import com.vkondrav.ram.app.screen.drawer.usecase.NavigateToRouteFromDrawerUseCase
import com.vkondrav.ram.app.screen.drawer.viewmodel.DrawerViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val drawerModule =  module {
    factory {
        NavigateToRouteFromDrawerUseCase(
            navigateToRouteUseCase = get(),
            drawerState = get(),
        )
    }
    factory {
        DrawerMenuSource(
            navigateToRouteFromDrawerUseCase = get(),
        )
    }
    viewModel {
        DrawerViewModel(
            drawerMenuSource = get(),
            dispatcher = get(),
        )
    }
}
