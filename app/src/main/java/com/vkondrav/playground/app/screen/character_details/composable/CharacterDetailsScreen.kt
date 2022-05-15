package com.vkondrav.playground.app.screen.character_details.composable

import androidx.compose.runtime.Composable
import com.vkondrav.playground.app.base.composable.screen.BaseScreen
import com.vkondrav.playground.app.screen.character_details.viewmodel.CharacterDetailsViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun CharacterDetailsScreen(
    id: String,
) {
    val viewModel = getViewModel<CharacterDetailsViewModel>().also { 
        it.fetchCharacterDetails(id)
    }
    
    BaseScreen(screenEventViewModel = viewModel)
}