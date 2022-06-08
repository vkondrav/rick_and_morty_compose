package com.vkondrav.playground.app.screen.location_details.di

import com.vkondrav.playground.app.screen.location_details.usecase.FetchLocationDetailsUseCase
import com.vkondrav.playground.app.screen.location_details.usecase.LocationDetailsSource
import com.vkondrav.playground.app.screen.location_details.viewmodel.LocationDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val locationDetailsModule = module {
    factory {
        FetchLocationDetailsUseCase(
            ramRepository = get(),
            favoriteLocationsDao = get(),
            favoriteCharactersDao = get(),
            sourceConstructor = get(),
        )
    }
    factory {
        LocationDetailsSource(
            fetchLocationDetailsUseCase = get(),
            fetchCollapsableDrawerState = get(),
            handleCollapsableDrawerUseCase = get(),
            charactersViewItemConstructor = get(),
        )
    }
    viewModel { params ->
        LocationDetailsViewModel(
            locationId = params.get(),
            locationDetailsSource = get(),
            dispatcher = get(),
        )
    }
}
