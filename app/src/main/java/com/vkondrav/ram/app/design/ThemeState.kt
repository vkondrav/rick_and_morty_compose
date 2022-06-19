package com.vkondrav.ram.app.design

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class ThemeState {

    private val _isDarkTheme = MutableStateFlow(true)
    val isDarkTheme: Flow<Boolean> = _isDarkTheme

    fun toggleTheme() {
        _isDarkTheme.tryEmit(!_isDarkTheme.value)
    }
}
