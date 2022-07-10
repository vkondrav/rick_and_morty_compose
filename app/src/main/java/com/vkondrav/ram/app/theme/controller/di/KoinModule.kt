package com.vkondrav.ram.app.theme.controller.di

import com.vkondrav.ram.app.theme.controller.usecase.FetchThemeStateUseCase
import com.vkondrav.ram.app.theme.controller.usecase.ToggleThemeUseCase
import org.koin.dsl.module

val themeControllerModule = module {
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
