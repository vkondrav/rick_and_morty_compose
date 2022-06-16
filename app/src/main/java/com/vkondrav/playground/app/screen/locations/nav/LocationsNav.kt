package com.vkondrav.playground.app.screen.locations.nav

import androidx.navigation.navArgument
import com.vkondrav.playground.app.R
import com.vkondrav.playground.app.common.navigation.NAV_TITLE
import com.vkondrav.playground.app.common.navigation.Screen
import com.vkondrav.playground.app.common.utils.TextResource
import com.vkondrav.playground.app.screen.locations.composable.LocationsScreen

val locationsScreen = Screen(
    route = "locations?$NAV_TITLE={$NAV_TITLE}",
    arguments = listOf(
        navArgument(NAV_TITLE) { defaultValue = TextResource.Resource(R.string.locations) },
    ),
) { LocationsScreen() }
