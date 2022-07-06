package com.vkondrav.ram.common.ui

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
