package com.vkondrav.ram.collapsable.drawer.view

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.vkondrav.ram.common.ui.design.DlsTheme
import com.vkondrav.ram.common.ui.view.Composable
import com.vkondrav.ram.common.ui.view.ComposableItem
import com.vkondrav.ram.common.ui.data.TextResource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun CollapsableView(item: CollapsableViewItem) {
    val drawerOpen by item.open.collectAsState(initial = false)

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .clickable {
                    item.onClickAction(!drawerOpen)
                },
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
                    } else {
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
        id = "d1",
        title = TextResource.Literal("Collapsable"),
        open = emptyFlow(),
        items = emptyList(),
        onClickAction = { },
    )
}

data class CollapsableViewItem(
    val id: String,
    val title: TextResource,
    val open: Flow<Boolean>,
    val items: List<ComposableItem>,
    val onClickAction: (Boolean) -> Unit,
) : ComposableItem {
    @Composable
    override fun Composable() = CollapsableView(this)
}
