package com.vkondrav.playground.app.common.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowCircleDown
import androidx.compose.material.icons.filled.ArrowCircleUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.vkondrav.playground.app.base.item.Composable
import com.vkondrav.playground.app.base.item.ComposableItem

@Composable
fun CollapsableView(item: CollapsableViewItem) {
    var drawerOpen by remember { mutableStateOf(item.open) }

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .background(Color.Gray)
                .clickable { drawerOpen = !drawerOpen }
        ) {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                val (title, icon) = createRefs()

                Text(
                    modifier = Modifier.constrainAs(title) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start, margin = 8.dp)
                    },
                    text = item.title
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
                        Icons.Default.ArrowCircleDown 
                    }else {
                        Icons.Default.ArrowCircleUp
                    },
                    contentDescription = "collapsable arrow",
                )
            }
        }
        AnimatedVisibility(visible = drawerOpen) {
            Column {
                item.items.Composable()
            }
        }
        Divider(color = Color.Black, thickness = 1.dp)
    }
}

@Preview
@Composable
private fun Preview() {
    CollapsableViewItem(
        title = "Collapsable",
        open = true,
        items = emptyList(),
    )
}

data class CollapsableViewItem(
    val title: String,
    val open: Boolean,
    val items: List<ComposableItem>
) : ComposableItem {
    @Composable
    override fun Composable() =
        CollapsableView(this)
}
