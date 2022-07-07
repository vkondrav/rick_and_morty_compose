package com.vkondrav.ram.app.screen.favorite_locations.nav

import com.vkondrav.ram.app.R
import com.vkondrav.ram.common.ui.data.TextResource
import com.vkondrav.ram.app.screen.favorite_locations.composable.FavoriteLocationsScreen
import com.vkondrav.ram.navigation.data.TabScreen

val favoriteLocationsTab = TabScreen(
    title = TextResource.Resource(R.string.locations),
) { FavoriteLocationsScreen() }
