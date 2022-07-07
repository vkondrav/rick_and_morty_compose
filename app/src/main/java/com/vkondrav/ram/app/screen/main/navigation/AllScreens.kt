package com.vkondrav.ram.app.screen.main.navigation

import com.vkondrav.ram.character.details.nav.characterDetailsScreen
import com.vkondrav.ram.episode.details.nav.episodeDetailsScreen
import com.vkondrav.ram.episode.all.nav.episodesScreen
import com.vkondrav.ram.location.details.nav.locationDetailsScreen
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
