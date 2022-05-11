package com.vkondrav.playground.app.common.composable

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vkondrav.playground.app.base.item.ComposableItem
import com.vkondrav.playground.app.base.item.OnComposableAction

data class PageErrorViewItem(val error: Throwable) : ComposableItem {
    @Composable
    override fun Composable(action: OnComposableAction) =
        PageErrorView(item = this, action = action)
}

@Composable
fun PageErrorView(item: PageErrorViewItem, action: OnComposableAction) {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {

        Column(
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
                .align(
                    Alignment.Center
                )
        ) {
            Icon(
                Icons.Default.Error,
                contentDescription = "error",
                modifier = Modifier
                    .width(24.dp)
                    .height(24.dp),
            )
            Text(
                text = item.error.message ?: "Unknown Error",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    PageErrorViewItem(Exception("Oh no")).Composable(action = { })
}