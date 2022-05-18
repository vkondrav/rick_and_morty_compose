package com.vkondrav.playground.app.screen.locations.composable

import androidx.compose.runtime.Composable
import com.vkondrav.playground.app.base.composable.screen.BaseScreen
import com.vkondrav.playground.app.screen.locations.viewmodel.LocationsViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun LocationsScreen() {
    val viewModel = getViewModel<LocationsViewModel>().also {
        it.fetchCharacters()
    }

    BaseScreen(
        screenEventViewModel = viewModel,
    )
}