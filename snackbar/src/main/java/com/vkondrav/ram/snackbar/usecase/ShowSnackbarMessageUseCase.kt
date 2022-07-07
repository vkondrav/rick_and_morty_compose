package com.vkondrav.ram.snackbar.usecase

import com.vkondrav.ram.snackbar.SnackbarController

interface ShowSnackbarMessageUseCase {
    operator fun invoke(message: String)
}

internal fun showSnackbarMessageUseCase(
    snackbarController: SnackbarController,
) = object : ShowSnackbarMessageUseCase {
    override fun invoke(message: String) {
        snackbarController.showMessage(message)
    }
}
