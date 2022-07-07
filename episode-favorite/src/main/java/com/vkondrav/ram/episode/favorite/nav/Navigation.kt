package com.vkondrav.ram.episode.favorite.nav

import com.vkondrav.ram.common.ui.data.TextResource
import com.vkondrav.ram.episode.favorite.R
import com.vkondrav.ram.episode.favorite.composable.FavoriteEpisodesScreen
import com.vkondrav.ram.navigation.data.TabScreen

val favoriteEpisodesTab = TabScreen(
    title = TextResource.Resource(R.string.episodes),
) { FavoriteEpisodesScreen() }
