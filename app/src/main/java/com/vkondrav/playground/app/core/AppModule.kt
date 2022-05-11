package com.vkondrav.playground.app.core

import com.vkondrav.playground.app.characters.di.charactersModule
import com.vkondrav.playground.app.drawer.di.drawerModule
import com.vkondrav.playground.app.main.di.mainActivityModule
import com.vkondrav.playground.app.page1.di.page1Module
import com.vkondrav.playground.app.page2.di.page2Module
import com.vkondrav.playground.app.page3.di.page3Module
import com.vkondrav.playground.graphql.ram.ramModules
import org.koin.core.KoinApplication

fun KoinApplication.appModules() = modules(
    listOf(
        ramModules,
        listOf(
            coreModule,
            mainActivityModule,
            page1Module,
            page2Module,
            page3Module,
            charactersModule,
            drawerModule,
        )
    ).flatten()
)