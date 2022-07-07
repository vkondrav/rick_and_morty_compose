package com.vkondrav.ram.episode.details.nav

import com.vkondrav.ram.episode.details.composable.EpisodeDetailsScreen
import com.vkondrav.ram.navigation.Routes
import com.vkondrav.ram.navigation.data.Screen
import com.vkondrav.ram.navigation.error.NavigationException
import com.vkondrav.ram.navigation.id

val episodeDetailsScreen by lazy {
    Screen(
        route = Routes.Episodes.Details(),
    ) { bundle ->
        EpisodeDetailsScreen(
            id = bundle?.id
                ?: throw NavigationException("Navigating to episode details screen with no id"),
        )
    }
}
