package com.vkondrav.playground.app.base.item

import androidx.compose.runtime.Composable

interface ComposableItem {
    @Composable
    fun Composable(action: OnComposableAction)
}

@Composable
fun List<ComposableItem>.Composable(action: OnComposableAction) {
    forEach { item ->
        item.Composable(action = action)
    }
}