package com.vkondrav.playground.app.screen.characters.composable

import androidx.compose.foundation.clickable
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
            sizeVariant = Avatar.SizeVariant.Large,
            modifier = Modifier.align(Alignment.CenterVertically),
        )
        Text(
            text = item.character.name,
            color = DlsTheme.colors.text,
            style = DlsTheme.typography.headline4,
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.CenterVertically)
                .fillMaxWidth(),
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
        ),
        onClickAction = { },
    ).Composable()
}
