package com.vkondrav.playground.app.screen.locations.nav

import androidx.navigation.navArgument
import com.vkondrav.playground.app.common.navigation.Screen
import com.vkondrav.playground.app.screen.locations.composable.LocationsScreen

val locationsScreen = Screen(
    route = "locations?title={title}",
    arguments = listOf(
        navArgument("title") { defaultValue = "Locations" },
    ),
) { LocationsScreen() }