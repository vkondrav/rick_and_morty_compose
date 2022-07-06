package com.vkondrav.ram.snackbar.usecase

import androidx.compose.material.SnackbarHostState
import com.vkondrav.ram.snackbar.SnackbarController

interface HandleSnackbarStateUseCase {
    suspend operator fun invoke(snackbarHostState: SnackbarHostState)
}

internal fun handleSnackbarStateUseCase(
    snackbarController: SnackbarController,
) = object : HandleSnackbarStateUseCase {
    override suspend operator fun invoke(snackbarHostState: SnackbarHostState) {
        snackbarController.handleSnackbarState(snackbarHostState)
    }
}
