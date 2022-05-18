package com.vkondrav.playground.app.screen.character_details.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.vkondrav.playground.app.base.composable.screen.BaseScreen
import com.vkondrav.playground.app.screen.character_details.viewmodel.CharacterDetailsViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun CharacterDetailsScreen(
    id: String,
    viewModel: CharacterDetailsViewModel = getViewModel(),
) {
    viewModel.fetchCharacterDetails(id)
    BaseScreen(screenEventViewModel = viewModel)
}

@Preview
@Composable
private fun Preview() {
    CharacterDetailsScreen(id = "1")
}