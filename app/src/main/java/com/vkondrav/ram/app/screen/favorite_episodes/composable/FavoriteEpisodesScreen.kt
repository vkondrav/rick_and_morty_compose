package com.vkondrav.ram.app.screen.favorite_episodes.composable

import androidx.compose.runtime.Composable
import com.vkondrav.ram.common.ui.screen.BaseStateScreen
import com.vkondrav.ram.app.screen.favorite_episodes.viewmodel.FavoriteEpisodesViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun FavoriteEpisodesScreen() {
    BaseStateScreen(
        viewModel = getViewModel<FavoriteEpisodesViewModel>(),
    )
}
