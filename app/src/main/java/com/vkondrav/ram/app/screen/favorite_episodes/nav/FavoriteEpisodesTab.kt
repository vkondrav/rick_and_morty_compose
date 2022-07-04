package com.vkondrav.ram.app.screen.favorite_episodes.nav

import com.vkondrav.ram.app.R
import com.vkondrav.ram.util.TextResource
import com.vkondrav.ram.app.screen.favorite_episodes.composable.FavoriteEpisodesScreen
import com.vkondrav.ram.app.tabs.nav.TabScreen

val favoriteEpisodesTab = TabScreen(
    title = TextResource.Resource(R.string.episodes),
) { FavoriteEpisodesScreen() }
