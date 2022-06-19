package com.vkondrav.ram.app.common.state

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class DrawerStateHolder {

    private val _isOpen = MutableStateFlow(false)
    val isOpen: Flow<Boolean> = _isOpen

    fun open() {
        _isOpen.tryEmit(true)
    }

    fun close() {
        _isOpen.tryEmit(false)
    }
}
