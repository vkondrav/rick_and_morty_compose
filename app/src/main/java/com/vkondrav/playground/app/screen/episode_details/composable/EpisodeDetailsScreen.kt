package com.vkondrav.playground.app.screen.episode_details.composable

import androidx.compose.runtime.Composable
import com.vkondrav.playground.app.base.composable.screen.BaseScreen
import com.vkondrav.playground.app.screen.episode_details.viewmodel.EpisodeDetailsViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun EpisodeDetailsScreen(
    id: String,
) {
    val viewModel = getViewModel<EpisodeDetailsViewModel>().also {
        it.fetchEpisodeDetails(id)
    }
    
    BaseScreen(screenEventViewModel = viewModel)
}