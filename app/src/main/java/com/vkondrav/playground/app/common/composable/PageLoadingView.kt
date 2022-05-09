package com.vkondrav.playground.app.common.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.vkondrav.playground.app.base.item.ComposableItem
import com.vkondrav.playground.app.base.item.OnComposableAction

@Composable
fun PageLoadingView(action: OnComposableAction) {
    Box(
        Modifier
            .background(Color.White)
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        CircularProgressIndicator(
            Modifier.align(Alignment.Center)
        )
    }
}

object PageLoadingViewItem : ComposableItem {
    @Composable
    override fun Composable(action: OnComposableAction) =
        PageLoadingView(action)
}

@Preview
@Composable
private fun Preview() {
    PageLoadingViewItem.Composable(action = { })
}