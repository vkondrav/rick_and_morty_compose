package com.vkondrav.ram.location.all.nav

import androidx.navigation.navArgument
import com.vkondrav.ram.common.ui.data.TextResource
import com.vkondrav.ram.location.all.R
import com.vkondrav.ram.location.all.composable.LocationsScreen
import com.vkondrav.ram.navigation.NAV_TITLE
import com.vkondrav.ram.navigation.Routes
import com.vkondrav.ram.navigation.data.Screen

val locationsScreen by lazy {
    Screen(
        route = Routes.Locations.All(),
        arguments = listOf(
            navArgument(NAV_TITLE) { defaultValue = TextResource.Resource(R.string.locations) },
        ),
    ) { LocationsScreen() }
}
