package com.vkondrav.playground.app.screen.characters.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarOutline
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vkondrav.playground.app.base.item.ComposableItem
import com.vkondrav.playground.app.common.composable.Avatar
import com.vkondrav.playground.app.design.DlsTheme
import com.vkondrav.playground.graphql.ram.domain.RamCharacter

@Composable
fun CharacterView(item: CharacterViewItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp)
            .clickable {
                item.onClickAction()
            },
    ) {
        Avatar(
            source = item.character.image,
            sizeVariant = Avatar.SizeVariant.XLarge,
            modifier = Modifier.align(Alignment.CenterVertically),
        )

        Text(
            text = item.character.name,
            color = DlsTheme.colors.text,
            style = DlsTheme.typography.headline6,
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.CenterVertically)
                .weight(1f),
        )

        var favorite by remember { mutableStateOf(item.character.favorite) }

        val starIcon = if (favorite) Icons.Default.Star else Icons.Default.StarOutline

        Icon(
            imageVector = starIcon,
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(end = 8.dp)
                .size(32.dp)
                .clickable {
                    item.character.favorite = !item.character.favorite
                    favorite = item.character.favorite
                },
        )
    }
}

data class CharacterViewItem(
    val character: RamCharacter,
    val onClickAction: () -> Unit,
) : ComposableItem {
    @Composable
    override fun Composable() = CharacterView(this)
}

@Preview
@Composable
private fun Preview() {
    CharacterViewItem(
        RamCharacter(
            id = "0",
            name = "Morty",
            status = "alive",
            species = "human",
            image = null,
            favorite = false,
        ),
        onClickAction = { },
    ).Composable()
}
