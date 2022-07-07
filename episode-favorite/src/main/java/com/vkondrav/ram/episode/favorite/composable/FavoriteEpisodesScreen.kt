package com.vkondrav.ram.episode.favorite.composable

import androidx.compose.runtime.Composable
import com.vkondrav.ram.common.ui.screen.BaseStateScreen
import com.vkondrav.ram.episode.favorite.viewmodel.FavoriteEpisodesViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun FavoriteEpisodesScreen() {
    BaseStateScreen(
        viewModel = getViewModel<FavoriteEpisodesViewModel>(),
    )
}
