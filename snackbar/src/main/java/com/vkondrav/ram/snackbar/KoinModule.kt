package com.vkondrav.ram.snackbar

import com.vkondrav.ram.snackbar.usecase.handleSnackbarStateUseCase
import com.vkondrav.ram.snackbar.usecase.showSnackbarMessageUseCase
import org.koin.dsl.module

val snackbarModule = module {
    factory {
        handleSnackbarStateUseCase(
            snackbarController = get(),
        )
    }
    factory {
        showSnackbarMessageUseCase(
            snackbarController = get(),
        )
    }
    single {
        SnackbarController()
    }
}
