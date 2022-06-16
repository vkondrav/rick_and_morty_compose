package com.vkondrav.ram.app.screen.locations.composable

import androidx.compose.runtime.Composable
import com.vkondrav.ram.app.base.composable.screen.BasePagingScreen
import com.vkondrav.ram.app.screen.locations.viewmodel.LocationsViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun LocationsScreen(
    viewModel: LocationsViewModel = getViewModel(),
) {
    BasePagingScreen(
        pagingViewModel = viewModel,
    )
}
