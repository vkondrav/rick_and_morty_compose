package com.vkondrav.playground.app.design

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class ThemeState(
    val initialValue: Boolean,
    var isThemeDark: MutableState<Boolean> = mutableStateOf(initialValue),
) {

    fun toggleTheme() {
        isThemeDark.value = !isThemeDark.value
    }
}
