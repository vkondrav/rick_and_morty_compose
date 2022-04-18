package com.vkondrav.playground.app.base.item

import androidx.compose.runtime.Composable

interface ComposableItem {
    @Composable
    fun Composable(action: OnComposableAction)
}