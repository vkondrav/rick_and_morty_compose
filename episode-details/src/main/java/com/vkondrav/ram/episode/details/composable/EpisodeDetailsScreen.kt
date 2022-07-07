package com.vkondrav.ram.episode.details.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.vkondrav.ram.common.ui.screen.BaseStateScreen
import com.vkondrav.ram.episode.details.viewmodel.EpisodeDetailsViewModel
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun EpisodeDetailsScreen(
    id: String,
    viewModel: EpisodeDetailsViewModel = getViewModel { parametersOf(id) },
) {
    BaseStateScreen(viewModel)
}

@Preview
@Composable
private fun Preview() {
    EpisodeDetailsScreen(id = "1")
}
