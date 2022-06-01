package com.vkondrav.playground.app.common.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.vkondrav.playground.app.base.item.Composable
import com.vkondrav.playground.app.base.item.ComposableItem
import com.vkondrav.playground.app.common.utils.TextResource
import com.vkondrav.playground.app.design.DlsTheme

@Composable
fun CollapsableView(item: CollapsableViewItem) {
    var drawerOpen by remember { mutableStateOf(item.open) }

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .clickable { drawerOpen = !drawerOpen },
        ) {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
            ) {
                val (title, icon) = createRefs()

                Text(
                    modifier = Modifier
                        .constrainAs(title) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start, margin = 8.dp)
                    },
                    text = item.title.string(),
                    style = DlsTheme.typography.headline3,
                    color = DlsTheme.colors.text,
                )
                Icon(
                    modifier = Modifier
                        .constrainAs(icon) {
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            end.linkTo(parent.end, margin = 8.dp)
                        }
                        .height(32.dp)
                        .width(32.dp),
                    imageVector = if (drawerOpen) {
                        Icons.Default.ArrowUpward
                    }else {
                        Icons.Default.ArrowDownward
                    },
                    contentDescription = null,
                )
            }
        }
        AnimatedVisibility(visible = drawerOpen) {
            Column {
                item.items.Composable()
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    CollapsableViewItem(
        title = TextResource.Literal("Collapsable"),
        open = true,
        items = emptyList(),
    )
}

data class CollapsableViewItem(
    val title: TextResource,
    val open: Boolean,
    val items: List<ComposableItem>,
) : ComposableItem {
    @Composable
    override fun Composable() = CollapsableView(this)
}
