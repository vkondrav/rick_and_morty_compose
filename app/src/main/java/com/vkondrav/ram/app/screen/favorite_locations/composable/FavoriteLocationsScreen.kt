package com.vkondrav.ram.app.screen.favorite_locations.composable

import androidx.compose.runtime.Composable
import com.vkondrav.ram.app.base.composable.screen.BaseStateScreen
import com.vkondrav.ram.app.screen.favorite_locations.viewmodel.FavoriteLocationsViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun FavoriteLocationsScreen() {
    BaseStateScreen(
        viewModel = getViewModel<FavoriteLocationsViewModel>(),
    )
}
