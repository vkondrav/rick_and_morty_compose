package com.vkondrav.playground.app.core

import com.vkondrav.playground.app.screen.characters.di.charactersModule
import com.vkondrav.playground.app.screen.drawer.di.drawerModule
import com.vkondrav.playground.app.screen.character_details.di.characterDetailsModule
import com.vkondrav.playground.graphql.ram.ramModules
import org.koin.core.KoinApplication

fun KoinApplication.appModules() = modules(
    listOf(
        ramModules,
        listOf(
            coreModule,
            charactersModule,
            characterDetailsModule,
            drawerModule,
        )
    ).flatten()
)