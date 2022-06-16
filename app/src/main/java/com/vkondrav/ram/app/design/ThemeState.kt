package com.vkondrav.ram.app.design

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable

data class ThemeState(
    private val initialValue: Boolean,
    var isThemeDark: MutableState<Boolean> = mutableStateOf(initialValue),
) {

    fun toggleTheme() {
        isThemeDark.value = !isThemeDark.value
    }

    companion object {
        fun saver() =
            Saver<ThemeState, Boolean>(
                save = { it.isThemeDark.value },
                restore = { ThemeState(it) },
            )
    }
}

@Composable
fun rememberThemeState(
    initialValue: Boolean,
): ThemeState {
    return rememberSaveable(saver = ThemeState.saver()) {
        ThemeState(initialValue)
    }
}
