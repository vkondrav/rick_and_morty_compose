package com.vkondrav.playground.app.screen.location_details.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.vkondrav.playground.app.base.composable.screen.BaseScreen
import com.vkondrav.playground.app.screen.location_details.viewmodel.LocationDetailsViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun LocationDetailsScreen(
    id: String,
    viewModel: LocationDetailsViewModel = getViewModel(),
) {
    viewModel.fetchLocationDetails(id)
    BaseScreen(screenEventViewModel = viewModel)
}

@Preview
@Composable
private fun Composable() {
    LocationDetailsScreen(id = "1")
}