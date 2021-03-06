package com.vkondrav.ram.common.ui.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun PageLoadingView() {
    Box(
        Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
    ) {
        CircularProgressIndicator(
            Modifier.align(Alignment.Center),
        )
    }
}

object PageLoadingViewItem : ComposableItem {
    @Composable
    override fun Composable() = PageLoadingView()
}

@Preview
@Composable
private fun Preview() {
    PageLoadingViewItem.Composable()
}
