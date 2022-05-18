package com.vkondrav.playground.app.screen.locations.di

import com.vkondrav.playground.app.screen.locations.usecase.FetchLocationsUseCase
import com.vkondrav.playground.app.screen.locations.usecase.NavigateToLocationDetailsUseCase
import com.vkondrav.playground.app.screen.locations.viewmodel.LocationsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val locationsModule = module {
    factory {
        FetchLocationsUseCase(
            ramRepository = get(),
        )
    }
    factory {
        NavigateToLocationDetailsUseCase(
            appState = get(),
        )
    }
    viewModel {
        LocationsViewModel(
            fetchLocationsUseCase = get(),
            navigateToLocationDetailsUseCase = get(),
            dispatcher = get(),
        )
    }
}