package com.vkondrav.ram.app.screen.locations.nav

import androidx.navigation.navArgument
import com.vkondrav.ram.app.R
import com.vkondrav.ram.app.common.navigation.NAV_TITLE
import com.vkondrav.ram.app.common.navigation.Screen
import com.vkondrav.ram.app.common.utils.TextResource
import com.vkondrav.ram.app.screen.locations.composable.LocationsScreen

val locationsScreen = Screen(
    route = "locations?$NAV_TITLE={$NAV_TITLE}",
    arguments = listOf(
        navArgument(NAV_TITLE) { defaultValue = TextResource.Resource(R.string.locations) },
    ),
) { LocationsScreen() }
