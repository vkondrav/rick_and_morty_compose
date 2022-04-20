package com.vkondrav.playground.app.page2.di

import com.vkondrav.playground.app.page2.viewmodel.Page2ScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val page2Module = module {
    viewModel {
        Page2ScreenViewModel(
            navController = get(),
            dispatcher = get(),
        )
    }
}