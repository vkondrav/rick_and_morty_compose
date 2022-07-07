package com.vkondrav.ram.episode.all.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.vkondrav.ram.common.ui.screen.BasePagingScreen
import com.vkondrav.ram.episode.all.viewmodel.EpisodesViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun EpisodesScreen(
    viewModel: EpisodesViewModel = getViewModel(),
) {
    BasePagingScreen(
        pagingViewModel = viewModel,
    )
}

@Preview
@Composable
private fun Preview() {
    EpisodesScreen()
}
