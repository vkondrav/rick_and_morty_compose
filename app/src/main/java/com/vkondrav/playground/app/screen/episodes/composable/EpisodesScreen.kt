package com.vkondrav.playground.app.screen.episodes.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.vkondrav.playground.app.base.composable.screen.BaseScreen
import com.vkondrav.playground.app.screen.episodes.viewmodel.EpisodesViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun EpisodesScreen(
    viewModel: EpisodesViewModel = getViewModel(),
) {
    viewModel.fetchEpisodes()
    BaseScreen(
        screenEventViewModel = viewModel,
    )
}

@Preview
@Composable
private fun Preview() {
    EpisodesScreen()
}
