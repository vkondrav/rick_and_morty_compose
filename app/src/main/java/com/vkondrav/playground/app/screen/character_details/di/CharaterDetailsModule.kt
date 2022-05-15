package com.vkondrav.playground.app.screen.character_details.di

import com.vkondrav.playground.app.screen.character_details.usecase.CharacterDetailsUseCase
import com.vkondrav.playground.app.screen.character_details.viewmodel.CharacterDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val characterDetailsModule = module {
    factory {
        CharacterDetailsUseCase(
            ramRepository = get(),
        )
    }
    viewModel {
        CharacterDetailsViewModel(
            characterDetailsUseCase = get(),
            dispatcher = get(),
        )
    }
}