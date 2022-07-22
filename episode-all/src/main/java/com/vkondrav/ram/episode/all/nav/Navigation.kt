package com.vkondrav.ram.episode.all.nav

import androidx.navigation.navArgument
import com.vkondrav.ram.common.ui.data.TextResource
import com.vkondrav.ram.episode.all.R
import com.vkondrav.ram.episode.all.composable.EpisodesScreen
import com.vkondrav.ram.navigation.NAV_TITLE
import com.vkondrav.ram.navigation.Routes
import com.vkondrav.ram.navigation.data.Screen

val episodesScreen by lazy {
    Screen(
        route = Routes.Episodes.All(),
        arguments = listOf(
            navArgument(NAV_TITLE) { defaultValue = TextResource.Resource(R.string.episodes) },
        ),
    ) { EpisodesScreen() }
}
