package com.vkondrav.playground.app.common.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vkondrav.playground.app.common.action.MessageCardAction
import com.vkondrav.playground.app.base.item.ComposableItem
import com.vkondrav.playground.app.base.item.OnComposableAction

@Composable
fun MessageCard(
    item: MessageCardItem,
    action: OnComposableAction,
) {

    Row(
        modifier = Modifier
            .background(Color.White)
            .clickable { action.invoke(MessageCardAction) }
    ) {
        Icon(
            item.image,
            contentDescription = "icon",
            modifier = Modifier
                .width(24.dp)
                .height(24.dp)
                .align(CenterVertically)
        )
        Text(
            text = item.message,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        )
    }
}

data class MessageCardItem(
    val message: String,
    val image: ImageVector,
): ComposableItem {
    @Composable
    override fun Composable(action: OnComposableAction) = MessageCard(
        item = this,
        action = action,
    )
}

@Preview
@Composable
private fun Preview() {
    MessageCardItem(
        "Test",
        Icons.Default.AccountCircle
    ).Composable(action = { })
}