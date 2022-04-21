package com.vkondrav.playground.app.drawer.di

import com.vkondrav.playground.app.core.composableScopeQualifier
import com.vkondrav.playground.app.drawer.viewmodel.DrawerViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val drawerModule =  module {
    viewModel {
        DrawerViewModel(
            navController = get(),
            drawerState = get(),
            composableScope = get(qualifier = composableScopeQualifier),
            dispatcher = get(),
        )
    }
}