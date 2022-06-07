package com.vkondrav.playground.app.screen.favorite_characters.composable

import androidx.compose.runtime.Composable
import com.vkondrav.playground.app.base.composable.screen.BaseStateScreen
import com.vkondrav.playground.app.screen.favorite_characters.viewmodel.FavoriteCharactersViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun FavoriteCharactersScreen(
    viewModel: FavoriteCharactersViewModel = getViewModel(),
) {
    BaseStateScreen(viewModel = viewModel)
}
