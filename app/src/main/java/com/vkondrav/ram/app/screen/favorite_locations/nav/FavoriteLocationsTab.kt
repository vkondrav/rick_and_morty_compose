package com.vkondrav.ram.app.screen.favorite_locations.nav

import com.vkondrav.ram.app.R
import com.vkondrav.ram.common.ui.TextResource
import com.vkondrav.ram.app.screen.favorite_locations.composable.FavoriteLocationsScreen
import com.vkondrav.ram.app.tabs.nav.TabScreen

val favoriteLocationsTab = TabScreen(
    title = TextResource.Resource(R.string.locations),
) { FavoriteLocationsScreen() }
