package com.vkondrav.playground.app.common.composable

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
import com.vkondrav.playground.app.base.item.OnComposableAction
import com.vkondrav.playground.app.common.action.NavigateAction
import com.vkondrav.playground.app.common.navigation.Screen

@Composable
fun MenuItem(item: MenuItemItem, action: OnComposableAction) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .clickable {
                    action(NavigateAction(item.screen.id))
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
    MenuItemItem(screen = Screen("1", "1") { }).Composable(action = { })
}

data class MenuItemItem(
    val screen: Screen,
) : ComposableItem {
    @Composable
    override fun Composable(action: OnComposableAction) = MenuItem(this, action)
}