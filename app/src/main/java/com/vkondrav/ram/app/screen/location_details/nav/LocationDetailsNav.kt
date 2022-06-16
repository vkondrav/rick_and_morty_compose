package com.vkondrav.ram.app.screen.location_details.nav

import com.vkondrav.ram.app.common.navigation.NAV_ID
import com.vkondrav.ram.app.common.navigation.NAV_TITLE
import com.vkondrav.ram.app.common.navigation.id
import com.vkondrav.ram.app.common.navigation.NavigationException
import com.vkondrav.ram.app.common.navigation.Screen
import com.vkondrav.ram.app.screen.location_details.composable.LocationDetailsScreen

private const val NAV_LOCATION_DETAILS = "location_details"

val locationDetailsScreen = Screen(
    route = "$NAV_LOCATION_DETAILS/{$NAV_ID}?$NAV_TITLE={$NAV_TITLE}",
) { bundle ->
    LocationDetailsScreen(
        id = bundle?.id
            ?: throw NavigationException("Navigating to location details screen with no id"),
    )
}

fun toLocationDetailsScreen(id: String, title: String) =
    "$NAV_LOCATION_DETAILS/$id?$NAV_TITLE=$title"
