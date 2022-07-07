package com.vkondrav.ram.location.details.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.vkondrav.ram.common.ui.screen.BaseStateScreen
import com.vkondrav.ram.location.details.viewmodel.LocationDetailsViewModel
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun LocationDetailsScreen(
    id: String,
    viewModel: LocationDetailsViewModel = getViewModel { parametersOf(id) },
) {
    BaseStateScreen(viewModel)
}

@Preview
@Composable
private fun Composable() {
    LocationDetailsScreen(id = "1")
}
