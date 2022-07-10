package com.vkondrav.ram.location.favorite.nav

import com.vkondrav.ram.common.ui.data.TextResource
import com.vkondrav.ram.location.favorite.R
import com.vkondrav.ram.location.favorite.composable.FavoriteLocationsScreen
import com.vkondrav.ram.common.ui.data.TabScreen

val favoriteLocationsTab = TabScreen(
    title = TextResource.Resource(R.string.locations),
) { FavoriteLocationsScreen() }
