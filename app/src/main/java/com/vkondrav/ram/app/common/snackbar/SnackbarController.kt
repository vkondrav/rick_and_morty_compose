package com.vkondrav.ram.app.common.snackbar

import androidx.compose.material.SnackbarHostState
import com.vkondrav.ram.domain.util.FlowWrapper
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectLatest

class SnackbarController(private val wrapper: FlowWrapper = FlowWrapper()) {

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
