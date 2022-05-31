package com.vkondrav.playground.app.screen.characters.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.vkondrav.playground.app.base.composable.screen.BaseScreen
import com.vkondrav.playground.app.screen.characters.viewmodel.CharactersViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun CharactersScreen(
    viewModel: CharactersViewModel = getViewModel(),
) {
    viewModel.fetchCharacters()

    BaseScreen(
        screenEventViewModel = viewModel,
    )
}

@Preview
@Composable
private fun Preview() {
    CharactersScreen()
}
