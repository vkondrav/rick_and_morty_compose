package com.vkondrav.playground.app.screen.episodes.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vkondrav.playground.app.base.item.ComposableItem
import com.vkondrav.playground.graphql.ram.domain.RamEpisode

@Composable
fun EpisodeView(item: EpisodeViewItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                item.onClickAction()
            }
    ) {
        Text(
            text = item.episode.title,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        )
    }
}

data class EpisodeViewItem(
    val episode: RamEpisode,
    val onClickAction: () -> Unit,
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
        ),
        onClickAction = { },
    ).Composable()
}
