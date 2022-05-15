package com.vkondrav.playground.app.screen.drawer.di

import com.vkondrav.playground.app.screen.drawer.usecase.DrawerMenuUseCase
import com.vkondrav.playground.app.screen.drawer.viewmodel.DrawerViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val drawerModule =  module {
    factory {
        DrawerMenuUseCase(
            appState = get(),
        )
    }
    viewModel {
        DrawerViewModel(
            drawerMenuUseCase = get(),
            dispatcher = get(),
        )
    }
}