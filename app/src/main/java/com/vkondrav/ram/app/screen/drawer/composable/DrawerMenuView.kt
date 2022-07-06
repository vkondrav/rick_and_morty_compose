package com.vkondrav.ram.app.screen.drawer.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vkondrav.ram.common.ui.ComposableItem
import com.vkondrav.ram.common.ui.TextResource
import com.vkondrav.ram.app.design.DlsTheme

@Composable
fun DrawerMenuView(item: DrawerMenuViewItem) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clickable {
                    item.onClickAction()
                },
        ) {
            Text(
                text = item.title.string(),
                color = DlsTheme.colors.text,
                style = DlsTheme.typography.headline1,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(8.dp),
            )
        }
    }
}

data class DrawerMenuViewItem(
    val title: TextResource,
    val onClickAction: () -> Unit,
) : ComposableItem {
    @Composable
    override fun Composable() = DrawerMenuView(this)
}

@Preview
@Composable
private fun Preview() {
    DrawerMenuViewItem(
        title = TextResource.Literal("Title"),
        onClickAction = { },
    ).Composable()
}
