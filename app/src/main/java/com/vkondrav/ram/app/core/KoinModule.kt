package com.vkondrav.ram.app.core

import com.vkondrav.ram.app.screen.character_details.di.characterDetailsModule
import com.vkondrav.ram.app.screen.drawer.di.drawerModule
import com.vkondrav.ram.app.screen.episode_details.di.episodeDetailsModule
import com.vkondrav.ram.episode.all.di.episodesModule
import com.vkondrav.ram.character.favorite.di.favoriteCharactersModule
import com.vkondrav.ram.app.screen.favorite_episodes.di.favoriteEpisodesModule
import com.vkondrav.ram.app.screen.favorite_locations.di.favoriteLocationsModule
import com.vkondrav.ram.app.screen.location_details.di.locationDetailsModule
import com.vkondrav.ram.location.all.di.locationsModule
import com.vkondrav.ram.app.screen.main.di.mainModule
import com.vkondrav.ram.character.all.di.charactersModule
import com.vkondrav.ram.collapsable.drawer.collapsableDrawerModule
import com.vkondrav.ram.datastore.dataStoreModule
import com.vkondrav.ram.domain.domainModule
import com.vkondrav.ram.graphql.ramModules
import com.vkondrav.ram.navigation.navigationModule
import com.vkondrav.ram.room.roomModule
import com.vkondrav.ram.snackbar.snackbarModule
import org.koin.core.KoinApplication

fun KoinApplication.appModules() = modules(
    listOf(
        ramModules,
        listOf(
            coreModule,
            navigationModule,
            snackbarModule,
            mainModule,
            collapsableDrawerModule,
            roomModule,
            dataStoreModule,
            charactersModule,
            characterDetailsModule,
            locationsModule,
            locationDetailsModule,
            episodesModule,
            episodeDetailsModule,
            drawerModule,
            domainModule,
            com.vkondrav.ram.character.favorite.di.favoriteCharactersModule,
            favoriteLocationsModule,
            favoriteEpisodesModule,
        ),
    ).flatten(),
)
