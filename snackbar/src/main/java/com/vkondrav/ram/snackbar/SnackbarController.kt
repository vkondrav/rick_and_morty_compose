package com.vkondrav.ram.snackbar

import androidx.compose.material.SnackbarHostState
import com.vkondrav.ram.common.util.FlowWrapper
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectLatest

internal class SnackbarController(private val wrapper: FlowWrapper = FlowWrapper()) {

    private val messages = MutableSharedFlow<String>(extraBufferCapacity = Int.MAX_VALUE)

    fun showMessage(message: String) {
        messages.tryEmit(message)
    }

    suspend fun handleSnackbarState(snackbarHostState: SnackbarHostState) {
        wrapper(messages).collectLatest { message ->
            snackbarHostState.currentSnackbarData?.dismiss()
            snackbarHostState.showSnackbar(message)
        }
    }
}
