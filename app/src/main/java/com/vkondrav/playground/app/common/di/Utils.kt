package com.vkondrav.playground.app.common.di

import androidx.compose.material.DrawerState
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

fun NavController.loadIntoKoin() {
    loadIntoKoin(this)
}

fun DrawerState.loadIntoKoin() {
    loadIntoKoin(this)
}

fun CoroutineScope.loadIntoKoin() {
    loadIntoKoin(this)
}

private inline fun <reified T> loadIntoKoin(item: T) {
    loadKoinModules(module {
        single { item }
    })
}