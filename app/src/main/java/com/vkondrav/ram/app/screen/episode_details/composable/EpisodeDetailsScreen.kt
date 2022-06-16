package com.vkondrav.ram.app.screen.episode_details.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.vkondrav.ram.app.base.composable.screen.BaseStateScreen
import com.vkondrav.ram.app.screen.episode_details.viewmodel.EpisodeDetailsViewModel
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun EpisodeDetailsScreen(
    id: String,
) {
    BaseStateScreen(
        viewModel = getViewModel<EpisodeDetailsViewModel> {
            parametersOf(id)
        },
    )
}

@Preview
@Composable
private fun Preview() {
    EpisodeDetailsScreen(id = "1")
}
