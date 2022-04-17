package com.vkondrav.playground.app.common.item

import androidx.compose.runtime.Composable

interface ComposableItem {
    @Composable
    fun Composable(action: OnComposableAction)
}