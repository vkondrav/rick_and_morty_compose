package com.vkondrav.ram.app

import android.content.Context
import com.vkondrav.ram.apollo.SERVER_URL
import com.vkondrav.ram.character.all.di.charactersModule
import com.vkondrav.ram.character.details.di.characterDetailsModule
import com.vkondrav.ram.character.favorite.di.favoriteCharactersModule
import com.vkondrav.ram.collapsable.drawer.collapsableDrawerModule
import com.vkondrav.ram.datastore.DATASTORE_NAME
import com.vkondrav.ram.datastore.dataStoreModule
import com.vkondrav.ram.domain.domainModule
import com.vkondrav.ram.drawer.di.drawerModule
import com.vkondrav.ram.episode.all.di.episodesModule
import com.vkondrav.ram.episode.details.di.episodeDetailsModule
import com.vkondrav.ram.episode.favorite.di.favoriteEpisodesModule
import com.vkondrav.ram.graphql.ramModules
import com.vkondrav.ram.location.all.di.locationsModule
import com.vkondrav.ram.location.details.di.locationDetailsModule
import com.vkondrav.ram.location.favorite.di.favoriteLocationsModule
import com.vkondrav.ram.navigation.navigationModule
import com.vkondrav.ram.room.DATABASE_NAME
import com.vkondrav.ram.room.roomModule
import com.vkondrav.ram.snackbar.snackbarModule
import com.vkondrav.ram.theme.controller.di.themeControllerModule
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.core.KoinApplication
import org.koin.dsl.module

fun KoinApplication.appModules() = modules(
    listOf(
        listOf(
            module {
                factory<Context> {
                    androidApplication()
                }
                factory {
                    Dispatchers.Default
                }
                factory(SERVER_URL) { "https://rickandmortyapi.com/graphql" }
                factory(DATABASE_NAME) { "ram_database" }
                factory(DATASTORE_NAME) { "settings" }
            },
            navigationModule,
            snackbarModule,
            drawerModule,
            themeControllerModule,
            collapsableDrawerModule,
            roomModule,
            dataStoreModule,
            charactersModule,
            characterDetailsModule,
            locationsModule,
            locationDetailsModule,
            episodesModule,
            episodeDetailsModule,
            domainModule,
            favoriteCharactersModule,
            favoriteLocationsModule,
            favoriteEpisodesModule,
        ),
        ramModules,
    ).flatten(),
)
