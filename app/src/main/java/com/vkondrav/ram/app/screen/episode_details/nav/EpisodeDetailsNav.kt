package com.vkondrav.ram.app.screen.episode_details.nav

import com.vkondrav.ram.navigation.NAV_ID
import com.vkondrav.ram.navigation.NAV_TITLE
import com.vkondrav.ram.navigation.id
import com.vkondrav.ram.navigation.data.Screen
import com.vkondrav.ram.app.screen.episode_details.composable.EpisodeDetailsScreen
import com.vkondrav.ram.navigation.error.NavigationException

private const val NAV_EPISODE_DETAILS = "episode_details"

val episodeDetailsScreen by lazy {
    Screen(
        route = "$NAV_EPISODE_DETAILS/{$NAV_ID}?$NAV_TITLE={$NAV_TITLE}",
    ) { bundle ->
        EpisodeDetailsScreen(
            id = bundle?.id
                ?: throw NavigationException("Navigating to episode details screen with no id"),
        )
    }
}

fun toEpisodeDetailsScreen(id: String, title: String) = "$NAV_EPISODE_DETAILS/$id?$NAV_TITLE=$title"
