package com.vkondrav.playground.app.screen.favorite_episodes.composable

import androidx.compose.runtime.Composable
import com.vkondrav.playground.app.base.composable.screen.BaseStateScreen
import com.vkondrav.playground.app.screen.favorite_episodes.viewmodel.FavoriteEpisodesViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun FavoriteEpisodesScreen() {
    BaseStateScreen(
        viewModel = getViewModel<FavoriteEpisodesViewModel>(),
    )
}
