package com.vkondrav.playground.app.page3.di

import com.vkondrav.playground.app.page3.viewmodel.Page3ScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val page3Module = module {
    viewModel {
        Page3ScreenViewModel(
            dispatcher = get(),
        )
    }
}