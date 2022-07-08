package com.vkondrav.ram.app.screen.main.di

import com.vkondrav.ram.app.screen.main.usecase.FetchThemeStateUseCase
import com.vkondrav.ram.app.screen.main.usecase.ToggleThemeUseCase
import org.koin.dsl.module

val mainModule = module {
    factory {
        FetchThemeStateUseCase(
            themeController = get(),
        )
    }
    factory {
        ToggleThemeUseCase(
            themeController = get(),
        )
    }
}
