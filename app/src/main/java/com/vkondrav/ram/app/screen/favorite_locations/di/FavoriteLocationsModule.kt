package com.vkondrav.ram.app.screen.favorite_locations.di

import com.vkondrav.ram.app.screen.favorite_locations.usecase.FetchFavoriteLocationsUseCase
import com.vkondrav.ram.app.screen.favorite_locations.viewmodel.FavoriteLocationsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteLocationsModule = module {
    factory {
        FetchFavoriteLocationsUseCase(
            favoriteLocationsDao = get(),
            adapter = get(),
            navigateToLocationDetailsUseCase = get(),
            handleLocationFavoriteUseCase = get(),
        )
    }
    viewModel {
        FavoriteLocationsViewModel(
            favoriteLocationsUseCase = get(),
            dispatcher = get(),
        )
    }
}
