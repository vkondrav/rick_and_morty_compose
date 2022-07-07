package com.vkondrav.ram.character.details.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.vkondrav.ram.common.ui.screen.BaseStateScreen
import com.vkondrav.ram.character.details.viewmodel.CharacterDetailsViewModel
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun CharacterDetailsScreen(
    id: String,
    viewModel: CharacterDetailsViewModel = getViewModel { parametersOf(id) },
) {
    BaseStateScreen(viewModel)
}

@Preview
@Composable
private fun Preview() {
    CharacterDetailsScreen(id = "1")
}
