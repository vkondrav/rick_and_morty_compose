package com.vkondrav.playground.app.screen.locations.composable

import androidx.compose.runtime.Composable
import com.vkondrav.playground.app.base.composable.screen.BaseScreen
import com.vkondrav.playground.app.screen.locations.viewmodel.LocationsViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun LocationsScreen(
    viewModel: LocationsViewModel = getViewModel(),
) {
    viewModel.fetchCharacters()
    BaseScreen(
        screenEventViewModel = viewModel,
    )
}
