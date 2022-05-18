package com.vkondrav.playground.app.screen.episodes.composable

import androidx.compose.runtime.Composable
import com.vkondrav.playground.app.base.composable.screen.BaseScreen
import com.vkondrav.playground.app.screen.episodes.viewmodel.EpisodesViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun EpisodesScreen() {
    val viewModel = getViewModel<EpisodesViewModel>().also {
        it.fetchEpisodes()
    }

    BaseScreen(
        screenEventViewModel = viewModel,
    )
}