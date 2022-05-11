package com.vkondrav.playground.app.main.di

import com.vkondrav.playground.app.main.viewmodel.MainActivityScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainActivityModule = module {
    viewModel {
        MainActivityScreenViewModel(
            appState = get(),
            dispatcher = get(),
        )
    }
}