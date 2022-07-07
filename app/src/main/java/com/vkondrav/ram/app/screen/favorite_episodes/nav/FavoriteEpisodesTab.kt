package com.vkondrav.ram.app.screen.favorite_episodes.nav

import com.vkondrav.ram.app.R
import com.vkondrav.ram.common.ui.data.TextResource
import com.vkondrav.ram.app.screen.favorite_episodes.composable.FavoriteEpisodesScreen
import com.vkondrav.ram.navigation.data.TabScreen

val favoriteEpisodesTab = TabScreen(
    title = TextResource.Resource(R.string.episodes),
) { FavoriteEpisodesScreen() }
