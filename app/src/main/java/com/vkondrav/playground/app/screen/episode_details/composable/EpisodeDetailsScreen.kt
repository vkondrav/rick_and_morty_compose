package com.vkondrav.playground.app.screen.episode_details.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.vkondrav.playground.app.base.composable.screen.BaseScreen
import com.vkondrav.playground.app.screen.episode_details.viewmodel.EpisodeDetailsViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun EpisodeDetailsScreen(
    id: String,
    viewModel: EpisodeDetailsViewModel = getViewModel(),
) {
    viewModel.fetchEpisodeDetails(id)
    BaseScreen(screenEventViewModel = viewModel)
}

@Preview
@Composable
private fun Preview() {
    EpisodeDetailsScreen(id = "1")
}
