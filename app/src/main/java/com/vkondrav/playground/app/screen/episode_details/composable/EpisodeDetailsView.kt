package com.vkondrav.playground.app.screen.episode_details.composable

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vkondrav.playground.app.base.item.ComposableItem
import com.vkondrav.playground.graphql.ram.domain.RamEpisode

@Composable
fun EpisodeDetailsView(
    item: EpisodeDetailsViewItem,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Icon(
            imageVector = Icons.Default.AccountCircle,
            contentDescription = "",
            modifier = Modifier
                .width(48.dp)
                .height(48.dp)
                .align(CenterHorizontally)
        )
        Text(
            text = item.episode.title,
            modifier = Modifier
                .wrapContentSize()
                .wrapContentHeight()
                .align(CenterHorizontally)
        )
    }
}

data class EpisodeDetailsViewItem(
    val episode: RamEpisode,
) : ComposableItem {

    @Composable
    override fun Composable() = EpisodeDetailsView(this)

}

@Preview
@Composable
private fun Preview() {
    EpisodeDetailsViewItem(
        RamEpisode(
            id = "1",
            title = "Morty",
            airDate = null,
        )
    ).Composable()
}