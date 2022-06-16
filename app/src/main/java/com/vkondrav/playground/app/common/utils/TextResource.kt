package com.vkondrav.playground.app.common.utils

import android.os.Parcelable
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class TextResource: Parcelable {

    @Parcelize
    data class Literal(val source: String): TextResource()
    @Parcelize
    data class Resource(@StringRes val source: Int): TextResource()

    @Composable
    fun string() = when(this) {
        is Literal -> source
        is Resource -> stringResource(id = source)
    }
}
