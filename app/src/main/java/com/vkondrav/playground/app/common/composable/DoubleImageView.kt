package com.vkondrav.playground.app.common.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.PersonAdd
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vkondrav.playground.app.base.item.ComposableItem
import com.vkondrav.playground.app.base.item.OnComposableAction
import com.vkondrav.playground.app.common.action.ImageCardAction

@Composable
fun DoubleImageView(item: DoubleImageViewItem, action: OnComposableAction) {
    Row(
        modifier = Modifier
            .background(Color.LightGray)
    ) {
        Icon(
            image = item.image1,
            imageTitle = item.image1Title,
            action = action,
        )
        Icon(
            image = item.image2,
            imageTitle = item.image2Title,
            action = action,
        )
    }
}

@Composable
private fun RowScope.Icon(
    image: ImageVector,
    imageTitle: String,
    action: OnComposableAction,
) {
    Column(
        modifier = Modifier
            .wrapContentHeight(Alignment.CenterVertically)
            .weight(1f)
    ) {
        Icon(
            image,
            contentDescription = imageTitle,
            modifier = Modifier
                .height(48.dp)
                .clickable { action.invoke(ImageCardAction(imageTitle)) }
                .fillMaxWidth(),
        )
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            text = imageTitle,
        )
    }
}

data class DoubleImageViewItem(
    val image1Title: String,
    val image1: ImageVector,
    val image2Title: String,
    val image2: ImageVector,
) : ComposableItem {
    @Composable
    override fun Composable(action: OnComposableAction) =
        DoubleImageView(this, action)
}

@Preview
@Composable
private fun Preview() {
    DoubleImageViewItem(
        image1Title = "Person 1",
        image1 = Icons.Rounded.PersonAdd,
        image2Title = "Person 2",
        image2 = Icons.Rounded.Person,
    ).Composable(action = { })
}
