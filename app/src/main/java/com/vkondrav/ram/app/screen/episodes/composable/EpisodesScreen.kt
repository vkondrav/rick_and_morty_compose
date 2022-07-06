package com.vkondrav.ram.app.screen.episodes.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.vkondrav.ram.common.ui.BasePagingScreen
import com.vkondrav.ram.app.screen.episodes.viewmodel.EpisodesViewModel
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
