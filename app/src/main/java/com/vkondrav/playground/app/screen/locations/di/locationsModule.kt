package com.vkondrav.playground.app.screen.locations.di

import com.vkondrav.playground.app.screen.locations.usecase.AddLocationToFavoritesUseCase
import com.vkondrav.playground.app.screen.locations.usecase.HandleLocationFavoriteUseCase
import com.vkondrav.playground.app.screen.locations.usecase.RemoveLocationFromFavoritesUseCase
import com.vkondrav.playground.app.screen.locations.usecase.FetchLocationsUseCase
import com.vkondrav.playground.app.screen.locations.usecase.NavigateToLocationDetailsUseCase
import com.vkondrav.playground.app.screen.locations.viewmodel.LocationsViewModel
import com.vkondrav.playground.graphql.ram.domain.RamLocationTransformer
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val locationsModule = module {
    factory {
        RamLocationTransformer
    }
    factory {
        FetchLocationsUseCase(
            ramRepository = get(),
            ramLocationTransformer = get(),
            favoriteLocationsDao = get(),
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
    viewModel {
        LocationsViewModel(
            fetchLocationsUseCase = get(),
            navigateToLocationDetailsUseCase = get(),
            dispatcher = get(),
        )
    }
}
