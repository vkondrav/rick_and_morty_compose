package com.vkondrav.playground.app.screen.location_details.composable

import androidx.compose.runtime.Composable
import com.vkondrav.playground.app.base.composable.screen.BaseScreen
import com.vkondrav.playground.app.screen.location_details.viewmodel.LocationDetailsViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun LocationDetailsScreen(
    id: String,
) {
    val viewModel = getViewModel<LocationDetailsViewModel>().also {
        it.fetchLocationDetails(id)
    }
    
    BaseScreen(screenEventViewModel = viewModel)
}