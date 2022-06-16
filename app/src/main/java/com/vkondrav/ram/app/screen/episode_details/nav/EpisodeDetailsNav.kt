package com.vkondrav.ram.app.screen.episode_details.nav

import com.vkondrav.ram.app.common.navigation.NAV_ID
import com.vkondrav.ram.app.common.navigation.NAV_TITLE
import com.vkondrav.ram.app.common.navigation.id
import com.vkondrav.ram.app.common.navigation.NavigationException
import com.vkondrav.ram.app.common.navigation.Screen
import com.vkondrav.ram.app.screen.episode_details.composable.EpisodeDetailsScreen

private const val NAV_EPISODE_DETAILS = "episode_details"

val episodeDetailsScreen = Screen(
    route = "$NAV_EPISODE_DETAILS/{$NAV_ID}?$NAV_TITLE={$NAV_TITLE}",
) { bundle ->
    EpisodeDetailsScreen(
        id = bundle?.id
            ?: throw NavigationException("Navigating to episode details screen with no id"),
    )
}

fun toEpisodeDetailsScreen(id: String, title: String) = "$NAV_EPISODE_DETAILS/$id?$NAV_TITLE=$title"
