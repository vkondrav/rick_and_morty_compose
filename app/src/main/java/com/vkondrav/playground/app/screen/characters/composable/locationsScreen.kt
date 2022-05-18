package com.vkondrav.playground.app.screen.characters.composable

import androidx.compose.runtime.Composable
import com.vkondrav.playground.app.base.composable.screen.BaseScreen
import com.vkondrav.playground.app.screen.characters.viewmodel.CharactersViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun CharactersScreen() {
    val viewModel = getViewModel<CharactersViewModel>().also {
        it.fetchCharacters()
    }

    BaseScreen(
        screenEventViewModel = viewModel,
    )
}