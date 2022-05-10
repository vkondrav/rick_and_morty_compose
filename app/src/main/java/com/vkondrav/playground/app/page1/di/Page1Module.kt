package com.vkondrav.playground.app.page1.di

import com.vkondrav.playground.app.page1.viewmodel.Page1ScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val page1Module = module {
    viewModel {
        Page1ScreenViewModel(
            appState = get(),
            dispatcher = get(),
        )
    }
}