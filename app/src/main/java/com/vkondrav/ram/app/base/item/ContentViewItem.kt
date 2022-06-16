package com.vkondrav.ram.app.base.item

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ContentView(item: ContentViewItem) {
    LazyColumn { //TODO: configure to use grid when available
        items(item.items) { item ->
            item.Composable()
        }
    }
}

class ContentViewItem(
    val items: List<ComposableItem>,
) : ComposableItem {

    @Composable
    override fun Composable() = ContentView(this)
}

@Preview
@Composable
private fun Preview() {
    ContentViewItem(emptyList()).Composable()
}
