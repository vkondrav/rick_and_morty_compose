package com.vkondrav.playground.app.common.utils

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

sealed class TextResource {
    data class Literal(val source: String): TextResource()
    data class Resource(@StringRes val source: Int): TextResource()

    @Composable
    fun string() = when(this) {
        is Literal -> source
        is Resource -> stringResource(id = source)
    }
}
