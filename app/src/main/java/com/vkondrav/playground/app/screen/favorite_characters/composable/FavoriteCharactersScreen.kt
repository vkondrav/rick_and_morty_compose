package com.vkondrav.playground.app.screen.favorite_characters.composable

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.vkondrav.playground.app.screen.favorite_characters.viewmodel.FavoriteCharactersViewModel
import com.vkondrav.playground.app.screen.favorite_characters.viewmodel.ScreenState
import com.vkondrav.playground.app.screen.favorite_characters.viewmodel.ScreenStateViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun FavoriteCharactersScreen(
    viewModel: FavoriteCharactersViewModel = getViewModel(),
) {
    BaseStateScreen(viewModel = viewModel)
}

@Composable
fun BaseStateScreen(
    viewModel: ScreenStateViewModel,
) {
    val items = viewModel.items.collectAsState(initial = emptyList()).value

    LazyColumn {
        items(items) { item ->
            item.Composable()
        }
    }
    viewModel.screenState.apply {
        when (val state = value) {
            is ScreenState.Error -> {
                state.item.Composable()
            }
            is ScreenState.Loading -> {
                state.item.Composable()
            }
            else -> {
                //no-op
            }
        }
    }
}
