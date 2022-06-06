package com.vkondrav.playground.app.screen.character_details.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.vkondrav.playground.app.screen.character_details.viewmodel.CharacterDetailsViewModel
import com.vkondrav.playground.app.screen.favorite_characters.composable.BaseStateScreen
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun CharacterDetailsScreen(
    id: String,
) {
    BaseStateScreen(
        viewModel = getViewModel<CharacterDetailsViewModel> {
            parametersOf(id)
        },
    )
}

@Preview
@Composable
private fun Preview() {
    CharacterDetailsScreen(id = "1")
}
