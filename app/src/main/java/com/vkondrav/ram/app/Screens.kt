package com.vkondrav.ram.app.screen.main.navigation

import com.vkondrav.ram.character.details.nav.characterDetailsScreen
import com.vkondrav.ram.episode.details.nav.episodeDetailsScreen
import com.vkondrav.ram.episode.all.nav.episodesScreen
import com.vkondrav.ram.location.details.nav.locationDetailsScreen
import com.vkondrav.ram.location.all.nav.locationsScreen
import com.vkondrav.ram.character.all.nav.charactersScreen
import com.vkondrav.ram.character.favorite.nav.favoriteCharactersTab
import com.vkondrav.ram.episode.favorite.nav.favoriteEpisodesTab
import com.vkondrav.ram.location.favorite.nav.favoriteLocationsTab

val screens = listOf(
    charactersScreen,
    characterDetailsScreen,
    locationsScreen,
    locationDetailsScreen,
    episodesScreen,
    episodeDetailsScreen,
)

val tabs = listOf(
    favoriteCharactersTab,
    favoriteEpisodesTab,
    favoriteLocationsTab,
)
