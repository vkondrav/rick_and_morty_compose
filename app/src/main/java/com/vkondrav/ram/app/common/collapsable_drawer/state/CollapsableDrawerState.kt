package com.vkondrav.ram.app.common.collapsable_drawer.state

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull

class SnackbarMessageStateHolder {

    private val _state = MutableStateFlow<String?>(null)
    val state: Flow<String> = _state.filterNotNull()

    fun showSnackbar(message: String) {
        _state.tryEmit(message)
    }
}

class CollapsableDrawerState {

    private val openDrawers = mutableSetOf<String>()

    private val _state = MutableSharedFlow<Set<String>>(replay = 1)
    val state: Flow<Set<String>> = _state

    fun open(id: String) {
        openDrawers.add(id)
        _state.tryEmit(openDrawers)
    }

    fun close(id: String) {
        openDrawers.remove(id)
        _state.tryEmit(openDrawers)
    }
}
