package com.vkondrav.ram.app.screen.location_details.nav

import com.vkondrav.ram.app.screen.location_details.composable.LocationDetailsScreen
import com.vkondrav.ram.navigation.Routes
import com.vkondrav.ram.navigation.data.Screen
import com.vkondrav.ram.navigation.error.NavigationException
import com.vkondrav.ram.navigation.id

val locationDetailsScreen by lazy {
    Screen(
        route = Routes.Locations.Details(),
    ) { bundle ->
        LocationDetailsScreen(
            id = bundle?.id
                ?: throw NavigationException("Navigating to location details screen with no id"),
        )
    }
}
