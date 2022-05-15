package com.vkondrav.playground.app.screen.characters.di

import com.vkondrav.playground.app.screen.characters.usecase.CharactersUseCase
import com.vkondrav.playground.app.screen.characters.viewmodel.CharactersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val charactersModule = module {
    factory {
        CharactersUseCase(
            ramRepository = get(),
            appState = get(),
        )
    }
    viewModel {
        CharactersViewModel(
            charactersUseCase = get(),
            dispatcher = get(),
        )
    }
}