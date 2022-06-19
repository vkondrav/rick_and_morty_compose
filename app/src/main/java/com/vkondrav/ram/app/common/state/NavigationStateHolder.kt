package com.vkondrav.ram.app.common.state

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull

class NavigationStateHolder {

    private val _nav = MutableStateFlow<Nav?>(null)
    val nav = _nav.filterNotNull()

    fun navigate(destination: String) {
        _nav.tryEmit(Nav.Route(destination = destination))
    }

    fun navigateBack() {
        _nav.tryEmit(Nav.Back)
    }
}
