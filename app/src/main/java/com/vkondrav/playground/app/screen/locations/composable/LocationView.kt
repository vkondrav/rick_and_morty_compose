package com.vkondrav.playground.app.screen.locations.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vkondrav.playground.app.base.item.ComposableItem
import com.vkondrav.playground.app.common.composable.Favorite
import com.vkondrav.playground.app.design.DlsTheme
import com.vkondrav.playground.app.design.dlsDarkColorPalette
import com.vkondrav.playground.app.design.dlsLightColorPalette
import com.vkondrav.playground.graphql.ram.domain.RamLocation

@Composable
fun LocationView(item: LocationViewItem) {
    Row(
        modifier = Modifier
            .padding(top = 8.dp, bottom = 8.dp)
            .fillMaxWidth()
            .clickable {
                item.onClickAction()
            },
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.CenterVertically)
                .weight(1f),
        ) {
            Text(
                text = item.location.name,
                color = DlsTheme.colors.text,
                style = DlsTheme.typography.headline6,
            )
            item.location.dimension?.let {
                Text(
                    text = it,
                    color = DlsTheme.colors.textSubtle,
                    style = DlsTheme.typography.subtitle,
                )
            }
        }

        Favorite(
            fav = item.location.favorite,
            onClickAction = { isFavorite ->
                item.onFavoriteAction(isFavorite)
            },
        )
    }
}

data class LocationViewItem(
    val location: RamLocation,
    val onClickAction: () -> Unit,
    val onFavoriteAction: (Boolean) -> Unit,
) : ComposableItem {
    @Composable
    override fun Composable() = LocationView(this)
}

@Composable
private fun PreviewItem(isFavorite: Boolean) {
    Surface(
        color = DlsTheme.colors.background,
    ) {
        LocationViewItem(
            RamLocation(
                id = "0",
                name = "Earth",
                dimension = "C-137",
                favorite = isFavorite,
            ),
            onClickAction = { },
            onFavoriteAction = { },
        ).Composable()
    }
}

@Preview
@Composable
private fun PreviewDark() {
    DlsTheme(colors = dlsDarkColorPalette()) {
        PreviewItem(isFavorite = true)
    }
}

@Preview
@Composable
private fun PreviewLight() {
    DlsTheme(colors = dlsLightColorPalette()) {
        PreviewItem(isFavorite = false)
    }
}
