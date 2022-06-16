package com.vkondrav.ram.app.screen.locations.di

import com.vkondrav.ram.app.screen.locations.usecase.AddLocationToFavoritesUseCase
import com.vkondrav.ram.app.screen.locations.usecase.HandleLocationFavoriteUseCase
import com.vkondrav.ram.app.screen.locations.usecase.RemoveLocationFromFavoritesUseCase
import com.vkondrav.ram.app.screen.locations.usecase.FetchLocationsUseCase
import com.vkondrav.ram.app.screen.locations.usecase.NavigateToLocationDetailsUseCase
import com.vkondrav.ram.app.screen.locations.usecase.LocationViewItemsConstructor
import com.vkondrav.ram.app.screen.locations.usecase.LocationsSource
import com.vkondrav.ram.app.screen.locations.viewmodel.LocationsViewModel
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
            snackbarHostState = get(),
            dispatcher = get(),
        )
    }
    factory {
        AddLocationToFavoritesUseCase(
            favoriteLocationsDao = get(),
            snackbarHostState = get(),
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
            navController = get(),
        )
    }
    factory {
        LocationViewItemsConstructor(
            navigateToLocationDetailsUseCase = get(),
            handleLocationFavoriteUseCase = get(),
        )
    }
    factory {
        LocationsSource(
            fetchLocationsUseCase = get(),
            locationViewItemsConstructor = get(),
        )
    }
    viewModel {
        LocationsViewModel(
            locationsSource = get(),
            dispatcher = get(),
        )
    }
}
