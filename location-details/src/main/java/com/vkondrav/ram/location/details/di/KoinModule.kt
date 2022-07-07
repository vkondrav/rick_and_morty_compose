package com.vkondrav.ram.location.details.di

import com.vkondrav.ram.location.details.usecase.FetchLocationDetailsUseCase
import com.vkondrav.ram.location.details.usecase.LocationDetailsSource
import com.vkondrav.ram.location.details.viewmodel.LocationDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val locationDetailsModule = module {
    factory {
        FetchLocationDetailsUseCase(
            ramRepository = get(),
            favoriteLocationsDao = get(),
            favoriteCharactersDao = get(),
            factory = get(),
        )
    }
    factory {
        LocationDetailsSource(
            fetchLocationDetailsUseCase = get(),
            fetchCollapsableDrawerState = get(),
            handleCollapsableDrawerUseCase = get(),
            characterViewItemFactory = get(),
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
