package com.vkondrav.playground.app.screen.favorite_locations.nav

import com.vkondrav.playground.app.R
import com.vkondrav.playground.app.common.utils.TextResource
import com.vkondrav.playground.app.screen.favorite_locations.composable.FavoriteLocationsScreen
import com.vkondrav.playground.app.tabs.nav.TabScreen

val favoriteLocationsTab = TabScreen(
    title = TextResource.Resource(R.string.locations),
) { FavoriteLocationsScreen() }
