package com.vkondrav.ram.app.screen.characters.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.vkondrav.ram.common.ui.BasePagingScreen
import com.vkondrav.ram.app.screen.characters.viewmodel.CharactersViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun CharactersScreen(
    viewModel: CharactersViewModel = getViewModel(),
) {
    BasePagingScreen(
        pagingViewModel = viewModel,
    )
}

@Preview
@Composable
private fun Preview() {
    CharactersScreen()
}
