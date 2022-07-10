package com.vkondrav.ram.theme.controller.di

import com.vkondrav.ram.theme.controller.core.ThemeController
import com.vkondrav.ram.theme.controller.usecase.FetchThemeStateUseCase
import com.vkondrav.ram.theme.controller.usecase.ToggleThemeUseCase
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
    single {
        ThemeController(
            dataStore = get(),
            dispatcher = get(),
        )
    }
}
