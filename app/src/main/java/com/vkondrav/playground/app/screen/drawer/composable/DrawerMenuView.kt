package com.vkondrav.playground.app.screen.drawer.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vkondrav.playground.app.base.item.ComposableItem
import com.vkondrav.playground.app.common.navigation.Screen

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
                text = item.screen.title,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(8.dp)
            )
        }
        Divider(color = Color.LightGray)
    }
}

@Preview
@Composable
private fun Preview() {
    DrawerMenuViewItem(
        screen = Screen("1", "1", emptyList()) { },
        onClickAction = { }
    ).Composable()
}

data class DrawerMenuViewItem(
    val screen: Screen,
    val onClickAction: () -> Unit,
) : ComposableItem {
    @Composable
    override fun Composable() = DrawerMenuView(this)
}