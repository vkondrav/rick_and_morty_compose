package com.vkondrav.ram.character.favorite.composable

import androidx.compose.runtime.Composable
import com.vkondrav.ram.character.favorite.viewmodel.FavoriteCharactersViewModel
import com.vkondrav.ram.common.ui.screen.BaseStateScreen
import org.koin.androidx.compose.getViewModel

@Composable
fun FavoriteCharactersScreen() {
    BaseStateScreen(
        viewModel = getViewModel<FavoriteCharactersViewModel>(),
    )
}
