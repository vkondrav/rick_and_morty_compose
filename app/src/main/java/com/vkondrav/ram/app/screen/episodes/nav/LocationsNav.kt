package com.vkondrav.ram.app.screen.episodes.nav

import androidx.navigation.navArgument
import com.vkondrav.ram.app.R
import com.vkondrav.ram.navigation.NAV_TITLE
import com.vkondrav.ram.navigation.data.Screen
import com.vkondrav.ram.common.ui.data.TextResource
import com.vkondrav.ram.app.screen.episodes.composable.EpisodesScreen

val episodesScreen by lazy {
    Screen(
        route = "episodes?$NAV_TITLE={$NAV_TITLE}",
        arguments = listOf(
            navArgument(NAV_TITLE) { defaultValue = TextResource.Resource(R.string.episodes) },
        ),
    ) { EpisodesScreen() }
}
