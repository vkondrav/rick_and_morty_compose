package com.vkondrav.ram.app.core

import com.vkondrav.ram.app.common.collapsable_drawer.di.collapsableDrawerModule
import com.vkondrav.ram.app.screen.character_details.di.characterDetailsModule
import com.vkondrav.ram.app.screen.characters.di.charactersModule
import com.vkondrav.ram.app.screen.drawer.di.drawerModule
import com.vkondrav.ram.app.screen.episode_details.di.episodeDetailsModule
import com.vkondrav.ram.app.screen.episodes.di.episodesModule
import com.vkondrav.ram.app.screen.favorite_characters.di.favoriteCharactersModule
import com.vkondrav.ram.app.screen.favorite_episodes.di.favoriteEpisodesModule
import com.vkondrav.ram.app.screen.favorite_locations.di.favoriteLocationsModule
import com.vkondrav.ram.app.screen.location_details.di.locationDetailsModule
import com.vkondrav.ram.app.screen.locations.di.locationsModule
import com.vkondrav.ram.domain.di.domainModule
import com.vkondrav.ram.graphql.ramModules
import com.vkondrav.ram.room.ram.roomModule
import org.koin.core.KoinApplication

fun KoinApplication.appModules() = modules(
    listOf(
        ramModules,
        listOf(
            coreModule,
            collapsableDrawerModule,
            roomModule,
            charactersModule,
            characterDetailsModule,
            locationsModule,
            locationDetailsModule,
            episodesModule,
            episodeDetailsModule,
            drawerModule,
            domainModule,
            favoriteCharactersModule,
            favoriteLocationsModule,
            favoriteEpisodesModule,
        ),
    ).flatten(),
)
