package com.vkondrav.ram.location.favorite.composable

import androidx.compose.runtime.Composable
import com.vkondrav.ram.common.ui.screen.BaseStateScreen
import com.vkondrav.ram.location.favorite.viewmodel.FavoriteLocationsViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun FavoriteLocationsScreen() {
    BaseStateScreen(
        viewModel = getViewModel<FavoriteLocationsViewModel>(),
    )
}
