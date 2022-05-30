package com.vkondrav.playground.app.screen.drawer.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vkondrav.playground.app.base.item.ComposableItem

@Composable
fun DrawerMenuView(item: DrawerMenuViewItem) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .clickable {
                    item.onClickAction()
                }
        ) {
            Text(
                text = item.title,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(8.dp)
            )
        }
        Divider(color = Color.LightGray)
    }
}

data class DrawerMenuViewItem(
    val title: String,
    val onClickAction: () -> Unit,
) : ComposableItem {
    @Composable
    override fun Composable() = DrawerMenuView(this)
}

@Preview
@Composable
private fun Preview() {
    DrawerMenuViewItem(
        title = "Title",
        onClickAction = { }
    ).Composable()
}
