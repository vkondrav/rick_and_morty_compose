package com.vkondrav.playground.app.screen.locations.di

import com.vkondrav.playground.app.screen.locations.usecase.AddLocationToFavoritesUseCase
import com.vkondrav.playground.app.screen.locations.usecase.HandleLocationFavoriteUseCase
import com.vkondrav.playground.app.screen.locations.usecase.RemoveLocationFromFavoritesUseCase
import com.vkondrav.playground.app.screen.locations.usecase.FetchLocationsUseCase
import com.vkondrav.playground.app.screen.locations.usecase.NavigateToLocationDetailsUseCase
import com.vkondrav.playground.app.screen.locations.usecase.TransformLocationsUseCase
import com.vkondrav.playground.app.screen.locations.usecase.LocationsSource
import com.vkondrav.playground.app.screen.locations.viewmodel.LocationsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val locationsModule = module {
    factory {
        FetchLocationsUseCase(
            ramRepository = get(),
            favoriteLocationsDao = get(),
            sourceConstructor = get(),
        )
    }
    factory {
        RemoveLocationFromFavoritesUseCase(
            favoriteLocationsDao = get(),
            appState = get(),
            dispatcher = get(),
        )
    }
    factory {
        AddLocationToFavoritesUseCase(
            favoriteLocationsDao = get(),
            appState = get(),
            dispatcher = get(),
        )
    }
    factory {
        HandleLocationFavoriteUseCase(
            addLocationToFavoritesUseCase = get(),
            removeLocationToFavoritesUseCase = get(),
        )
    }
    factory {
        NavigateToLocationDetailsUseCase(
            appState = get(),
        )
    }
    factory {
        TransformLocationsUseCase(
            navigateToLocationDetailsUseCase = get(),
            handleLocationFavoriteUseCase = get(),
        )
    }
    factory {
        LocationsSource(
            fetchLocationsUseCase = get(),
            transformLocationsUseCase = get(),
        )
    }
    viewModel {
        LocationsViewModel(
            locationsSource = get(),
            dispatcher = get(),
        )
    }
}
