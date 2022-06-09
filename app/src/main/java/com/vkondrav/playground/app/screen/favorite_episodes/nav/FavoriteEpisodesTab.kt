package com.vkondrav.playground.app.screen.favorite_episodes.nav

import com.vkondrav.playground.app.R
import com.vkondrav.playground.app.common.utils.TextResource
import com.vkondrav.playground.app.screen.favorite_episodes.composable.FavoriteEpisodesScreen
import com.vkondrav.playground.app.tabs.nav.TabScreen

val favoriteEpisodesTab = TabScreen(
    title = TextResource.Resource(R.string.episodes),
) { FavoriteEpisodesScreen() }
