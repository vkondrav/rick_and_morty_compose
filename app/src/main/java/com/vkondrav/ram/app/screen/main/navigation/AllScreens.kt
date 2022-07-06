package com.vkondrav.ram.app.screen.main.navigation

import com.vkondrav.ram.app.screen.character_details.nav.characterDetailsScreen
import com.vkondrav.ram.app.screen.characters.nav.charactersScreen
import com.vkondrav.ram.app.screen.episode_details.nav.episodeDetailsScreen
import com.vkondrav.ram.app.screen.episodes.nav.episodesScreen
import com.vkondrav.ram.app.screen.location_details.nav.locationDetailsScreen
import com.vkondrav.ram.app.screen.locations.nav.locationsScreen

val allScreens = listOf(
    charactersScreen,
    characterDetailsScreen,
    locationsScreen,
    locationDetailsScreen,
    episodesScreen,
    episodeDetailsScreen,
)
