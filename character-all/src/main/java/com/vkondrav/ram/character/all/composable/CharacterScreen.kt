package com.vkondrav.ram.character.all.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.vkondrav.ram.common.ui.screen.BasePagingScreen
import com.vkondrav.ram.character.all.viewmodel.CharactersViewModel
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
