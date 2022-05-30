package com.vkondrav.playground.app.base.item

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun LazyColumnView(item: LazyColumnViewItem) {
    LazyColumn {
        items(item.items) { item ->
            item.Composable()
        }
    }
}

class LazyColumnViewItem(
    val items: List<ComposableItem>,
) : ComposableItem {

    @Composable
    override fun Composable() = LazyColumnView(this)
}

@Preview
@Composable
private fun Preview() {
    LazyColumnViewItem(emptyList()).Composable()
}