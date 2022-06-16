package com.vkondrav.ram.app.screen.characters.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vkondrav.ram.app.base.item.ComposableItem
import com.vkondrav.ram.app.common.composable.Avatar
import com.vkondrav.ram.app.common.composable.Favorite
import com.vkondrav.ram.app.design.DlsTheme
import com.vkondrav.ram.app.design.dlsDarkColorPalette
import com.vkondrav.ram.app.design.dlsLightColorPalette
import com.vkondrav.ram.domain.RamCharacter
import kotlinx.coroutines.flow.emptyFlow

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

        Column(
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.CenterVertically)
                .weight(1f),
        ) {
            Text(
                text = item.character.name,
                color = DlsTheme.colors.text,
                style = DlsTheme.typography.headline6,
            )
            item.character.status?.let {
                Text(
                    text = it,
                    color = DlsTheme.colors.textSubtle,
                    style = DlsTheme.typography.subtitle,
                )
            }
        }

        val favorite by item.character.isFavorite. collectAsState(initial = false)

        Favorite(
            favorite = favorite,
            onClickAction = { isFavorite ->
                item.onFavoriteAction(isFavorite)
            },
        )
    }
}

data class CharacterViewItem(
    val character: RamCharacter,
    val onClickAction: () -> Unit,
    val onFavoriteAction: (Boolean) -> Unit,
) : ComposableItem {
    @Composable
    override fun Composable() = CharacterView(this)
}

@Composable
private fun PreviewItem() {
    Surface(
        color = DlsTheme.colors.background,
    ) {
        CharacterViewItem(
            RamCharacter(
                id = "0",
                name = "Morty",
                status = "alive",
                species = "human",
                image = null,
                isFavorite = emptyFlow(),
            ),
            onClickAction = { },
            onFavoriteAction = { },
        ).Composable()
    }
}

@Preview
@Composable
private fun PreviewDark() {
    DlsTheme(
        colors = dlsDarkColorPalette,
    ) {
        PreviewItem()
    }
}

@Preview
@Composable
private fun PreviewLight() {
    DlsTheme(
        colors = dlsLightColorPalette,
    ) {
        PreviewItem()
    }
}
