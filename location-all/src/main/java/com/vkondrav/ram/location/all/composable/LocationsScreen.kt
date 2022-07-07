package com.vkondrav.ram.location.all.composable

import androidx.compose.runtime.Composable
import com.vkondrav.ram.common.ui.screen.BasePagingScreen
import com.vkondrav.ram.location.all.viewmodel.LocationsViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun LocationsScreen(
    viewModel: LocationsViewModel = getViewModel(),
) {
    BasePagingScreen(
        pagingViewModel = viewModel,
    )
}
