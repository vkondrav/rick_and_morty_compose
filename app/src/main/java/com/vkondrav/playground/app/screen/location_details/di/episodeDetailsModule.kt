package com.vkondrav.playground.app.screen.location_details.di

import com.vkondrav.playground.app.screen.location_details.usecase.FetchLocationDetailsUseCase
import com.vkondrav.playground.app.screen.location_details.viewmodel.LocationDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val locationDetailsModule = module {
    factory {
        FetchLocationDetailsUseCase(
            ramRepository = get(),
        )
    }
    viewModel {
        LocationDetailsViewModel(
            fetchLocationDetailsUseCase = get(),
            dispatcher = get(),
        )
    }
}
