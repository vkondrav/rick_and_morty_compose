package com.vkondrav.ram.app.screen.main.navigation

import com.vkondrav.ram.app.screen.character_details.nav.characterDetailsScreen
import com.vkondrav.ram.app.screen.episode_details.nav.episodeDetailsScreen
import com.vkondrav.ram.episode.all.nav.episodesScreen
import com.vkondrav.ram.app.screen.location_details.nav.locationDetailsScreen
import com.vkondrav.ram.location.all.nav.locationsScreen
import com.vkondrav.ram.character.all.nav.charactersScreen

val allScreens = listOf(
    charactersScreen,
    characterDetailsScreen,
    locationsScreen,
    locationDetailsScreen,
    episodesScreen,
    episodeDetailsScreen,
)
