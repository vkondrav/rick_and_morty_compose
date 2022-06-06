package com.vkondrav.playground.app.screen.episodes.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vkondrav.playground.app.base.item.ComposableItem
import com.vkondrav.playground.app.common.composable.Favorite
import com.vkondrav.playground.app.design.DlsTheme
import com.vkondrav.playground.domain.RamEpisode

@Composable
fun EpisodeView(item: EpisodeViewItem) {
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
                text = item.episode.title,
                color = DlsTheme.colors.text,
                style = DlsTheme.typography.headline6,
            )
            item.episode.airDate?.let {
                Text(
                    text = it,
                    color = DlsTheme.colors.textSubtle,
                    style = DlsTheme.typography.subtitle,
                )
            }
        }

        Favorite(
            fav = item.episode.isFavorite,
            onClickAction = { isFavorite ->
                item.episode.isFavorite = isFavorite
                item.onFavoriteAction(isFavorite)
            },
        )
    }
}

data class EpisodeViewItem(
    val episode: RamEpisode,
    val onClickAction: () -> Unit,
    val onFavoriteAction: (Boolean) -> Unit,
) : ComposableItem {
    @Composable
    override fun Composable() = EpisodeView(this)
}

@Preview
@Composable
private fun Preview() {
    EpisodeViewItem(
        RamEpisode(
            id = "1",
            title = "Episode 1",
            airDate = "1/2/2022",
            isFavorite = false,
        ),
        onClickAction = { },
        onFavoriteAction = { },
    ).Composable()
}
