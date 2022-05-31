package com.vkondrav.playground.app.common.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
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

@Composable
fun PageErrorView(item: PageErrorViewItem) {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
                .align(
                    Alignment.Center,
                ),
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
                modifier = Modifier.align(Alignment.CenterHorizontally),
            )
        }
    }
}

data class PageErrorViewItem(val error: Throwable) : ComposableItem {
    @Composable
    override fun Composable() = PageErrorView(item = this)
}

@Preview
@Composable
private fun Preview() {
    PageErrorViewItem(Exception("Oh no")).Composable()
}
