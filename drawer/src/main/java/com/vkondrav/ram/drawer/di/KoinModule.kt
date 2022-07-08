package com.vkondrav.ram.drawer.di

import com.vkondrav.ram.drawer.core.DrawerController
import com.vkondrav.ram.drawer.source.DrawerMenuSource
import com.vkondrav.ram.drawer.usecase.handleDrawerStateUseCase
import com.vkondrav.ram.drawer.usecase.navigateToRouteFromDrawerUseCase
import com.vkondrav.ram.drawer.usecase.openDrawerUseCase
import com.vkondrav.ram.drawer.viewmodel.DrawerViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val drawerModule = module {
    factory {
        handleDrawerStateUseCase(
            drawerController = get(),
        )
    }
    factory {
        openDrawerUseCase(
            drawerController = get(),
        )
    }
    factory {
        navigateToRouteFromDrawerUseCase(
            navigateToRouteUseCase = get(),
            drawerState = get(),
        )
    }
    factory {
        DrawerMenuSource(
            navigateToRouteFromDrawerUseCase = get(),
        )
    }
    single {
        DrawerController()
    }
    viewModel {
        DrawerViewModel(
            drawerMenuSource = get(),
            dispatcher = get(),
        )
    }
}
