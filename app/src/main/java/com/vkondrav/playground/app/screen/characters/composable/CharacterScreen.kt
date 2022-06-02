package com.vkondrav.playground.app.screen.characters.composable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.vkondrav.playground.app.base.composable.screen.BaseScreen
import com.vkondrav.playground.app.screen.characters.viewmodel.CharactersViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun CharactersScreen(
    viewModel: CharactersViewModel = getViewModel(),
) {
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    LaunchedEffect(viewModel, lifecycle) {
        lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
            viewModel.fetchCharacters()
        }
    }

    BaseScreen(
        screenEventViewModel = viewModel,
    )
}

@Preview
@Composable
private fun Preview() {
    CharactersScreen()
}
