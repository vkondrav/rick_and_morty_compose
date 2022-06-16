package com.vkondrav.ram.app.screen.favorite_characters.composable

import androidx.compose.runtime.Composable
import com.vkondrav.ram.app.base.composable.screen.BaseStateScreen
import com.vkondrav.ram.app.screen.favorite_characters.viewmodel.FavoriteCharactersViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun FavoriteCharactersScreen() {
    BaseStateScreen(
        viewModel = getViewModel<FavoriteCharactersViewModel>(),
    )
}
