package com.vkondrav.playground.app.screen.episodes.nav

import androidx.navigation.navArgument
import com.vkondrav.playground.app.R
import com.vkondrav.playground.app.common.navigation.NAV_TITLE
import com.vkondrav.playground.app.common.navigation.Screen
import com.vkondrav.playground.app.common.utils.TextResource
import com.vkondrav.playground.app.screen.episodes.composable.EpisodesScreen

val episodesScreen = Screen(
    route = "episodes?$NAV_TITLE={$NAV_TITLE}",
    arguments = listOf(
        navArgument(NAV_TITLE) { defaultValue = TextResource.Resource(R.string.episodes) },
    ),
) { EpisodesScreen() }
