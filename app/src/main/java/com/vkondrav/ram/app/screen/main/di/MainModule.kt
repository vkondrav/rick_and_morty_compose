package com.vkondrav.ram.app.screen.main.di

import com.vkondrav.ram.app.screen.main.usecase.FetchThemeStateUseCase
import com.vkondrav.ram.app.screen.main.usecase.HandleDrawerStateUseCase
import com.vkondrav.ram.app.screen.main.usecase.HandleSnackbarStateUseCase
import com.vkondrav.ram.app.screen.main.usecase.OpenDrawerUseCase
import com.vkondrav.ram.app.screen.main.usecase.ToggleThemeUseCase
import org.koin.dsl.module

val mainModule = module {
    factory {
        FetchThemeStateUseCase(
            themeController = get(),
        )
    }
    factory {
        HandleDrawerStateUseCase(
            drawerController = get(),
        )
    }
    factory {
        HandleSnackbarStateUseCase(
            snackbarController = get(),
        )
    }
    factory {
        OpenDrawerUseCase(
            drawerController = get(),
        )
    }
    factory {
        ToggleThemeUseCase(
            themeController = get(),
        )
    }
}
