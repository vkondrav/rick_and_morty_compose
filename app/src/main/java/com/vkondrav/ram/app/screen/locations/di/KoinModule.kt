package com.vkondrav.ram.app.screen.locations.di

import com.vkondrav.ram.app.screen.locations.usecase.AddLocationToFavoritesUseCase
import com.vkondrav.ram.app.screen.locations.usecase.HandleLocationFavoriteUseCase
import com.vkondrav.ram.app.screen.locations.usecase.RemoveLocationFromFavoritesUseCase
import com.vkondrav.ram.app.screen.locations.usecase.FetchLocationsUseCase
import com.vkondrav.ram.app.screen.locations.usecase.NavigateToLocationDetailsUseCase
import com.vkondrav.ram.app.screen.locations.factory.LocationViewItemFactory
import com.vkondrav.ram.app.screen.locations.source.LocationsSource
import com.vkondrav.ram.app.screen.locations.viewmodel.LocationsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val locationsModule = module {
    factory {
        FetchLocationsUseCase(
            ramRepository = get(),
            favoriteLocationsDao = get(),
            factory = get(),
        )
    }
    factory {
        RemoveLocationFromFavoritesUseCase(
            favoriteLocationsDao = get(),
            showSnackbarMessageUseCase = get(),
            dispatcher = get(),
        )
    }
    factory {
        AddLocationToFavoritesUseCase(
            favoriteLocationsDao = get(),
            showSnackbarMessageUseCase = get(),
            adapter = get(),
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
            navigateToRouteUseCase = get(),
        )
    }
    factory {
        LocationViewItemFactory(
            navigateToLocationDetailsUseCase = get(),
            handleLocationFavoriteUseCase = get(),
        )
    }
    factory {
        LocationsSource(
            fetchLocationsUseCase = get(),
            locationViewItemFactory = get(),
        )
    }
    viewModel {
        LocationsViewModel(
            locationsSource = get(),
            dispatcher = get(),
        )
    }
}
