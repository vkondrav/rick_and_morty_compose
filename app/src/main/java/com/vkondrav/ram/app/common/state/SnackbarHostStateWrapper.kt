package com.vkondrav.ram.app.common.state

import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHostState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class SnackbarHostStateWrapper(
    private val snackbarHostState: SnackbarHostState,
    private val scope: CoroutineScope,
) {
    fun showSnackbar(
        message: String,
        actionLabel: String? = null,
        duration: SnackbarDuration = SnackbarDuration.Short,
    ) {
        scope.launch {
            with(snackbarHostState) {
                currentSnackbarData?.dismiss()
                showSnackbar(message, actionLabel, duration)
            }
        }
    }
}
