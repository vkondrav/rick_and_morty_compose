package com.vkondrav.playground.app.screen.episodes.nav

import androidx.navigation.navArgument
import com.vkondrav.playground.app.common.navigation.Screen
import com.vkondrav.playground.app.screen.episodes.composable.EpisodesScreen

val episodesScreen = Screen(
    route = "episodes?title={title}",
    arguments = listOf(
        navArgument("title") { defaultValue = "Episodes" },
    ),
) { EpisodesScreen() }
