package com.vkondrav.ram.app.screen.main.usecase

import androidx.compose.material.SnackbarHostState
import com.vkondrav.ram.app.common.snackbar.SnackbarController

class HandleSnackbarStateUseCase(
    private val snackbarController: SnackbarController,
) {
    suspend operator fun invoke(snackbarHostState: SnackbarHostState) =
        snackbarController.handleSnackbarState(snackbarHostState)
}
