package com.vkondrav.playground.app.common.collapsable_drawer.state

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

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
