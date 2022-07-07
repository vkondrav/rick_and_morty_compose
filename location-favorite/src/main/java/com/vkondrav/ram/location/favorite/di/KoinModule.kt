package com.vkondrav.ram.location.favorite.di

import com.vkondrav.ram.location.favorite.usecase.FetchFavoriteLocationsUseCase
import com.vkondrav.ram.location.favorite.viewmodel.FavoriteLocationsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteLocationsModule = module {
    factory {
        FetchFavoriteLocationsUseCase(
            favoriteLocationsDao = get(),
            factory = get(),
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
