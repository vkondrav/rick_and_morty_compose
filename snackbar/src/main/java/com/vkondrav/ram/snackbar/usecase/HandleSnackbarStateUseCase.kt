package com.vkondrav.ram.snackbar.usecase

import androidx.compose.material.SnackbarHostState
import com.vkondrav.ram.snackbar.SnackbarController

class HandleSnackbarStateUseCase(
    private val snackbarController: SnackbarController,
) {
    suspend operator fun invoke(snackbarHostState: SnackbarHostState) =
        snackbarController.handleSnackbarState(snackbarHostState)
}
