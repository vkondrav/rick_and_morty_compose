package com.vkondrav.playground.app.core

import com.vkondrav.playground.app.screen.character_details.di.characterDetailsModule
import com.vkondrav.playground.app.screen.characters.di.charactersModule
import com.vkondrav.playground.app.screen.drawer.di.drawerModule
import com.vkondrav.playground.app.screen.episode_details.di.episodeDetailsModule
import com.vkondrav.playground.app.screen.episodes.di.episodesModule
import com.vkondrav.playground.app.screen.location_details.di.locationDetailsModule
import com.vkondrav.playground.app.screen.locations.di.locationsModule
import com.vkondrav.playground.domain.di.domainModule
import com.vkondrav.playground.graphql.ram.ramModules
import com.vkondrav.playground.room.ram.roomModule
import org.koin.core.KoinApplication

fun KoinApplication.appModules() = modules(
    listOf(
        ramModules,
        listOf(
            coreModule,
            roomModule,
            charactersModule,
            characterDetailsModule,
            locationsModule,
            locationDetailsModule,
            episodesModule,
            episodeDetailsModule,
            drawerModule,
            domainModule,
        ),
    ).flatten(),
)
