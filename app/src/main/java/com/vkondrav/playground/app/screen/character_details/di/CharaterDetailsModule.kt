package com.vkondrav.playground.app.screen.character_details.di

import com.vkondrav.playground.app.screen.character_details.usecase.FetchCharacterDetailsUseCase
import com.vkondrav.playground.app.screen.character_details.viewmodel.CharacterDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val characterDetailsModule = module {
    factory {
        FetchCharacterDetailsUseCase(
            ramRepository = get(),
        )
    }
    viewModel {
        CharacterDetailsViewModel(
            fetchCharacterDetailsUseCase = get(),
            dispatcher = get(),
        )
    }
}