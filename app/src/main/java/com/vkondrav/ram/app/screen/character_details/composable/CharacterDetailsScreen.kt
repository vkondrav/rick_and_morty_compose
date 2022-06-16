package com.vkondrav.ram.app.screen.character_details.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.vkondrav.ram.app.base.composable.screen.BaseStateScreen
import com.vkondrav.ram.app.screen.character_details.viewmodel.CharacterDetailsViewModel
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
