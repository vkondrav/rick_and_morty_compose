package com.vkondrav.ram.app.base.item

import androidx.compose.runtime.Composable

interface ComposableItem {
    @Composable
    fun Composable()
}

@Composable
fun List<ComposableItem>.Composable() {
    forEach { item ->
        item.Composable()
    }
}
