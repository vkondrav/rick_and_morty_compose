package com.vkondrav.playground.app.screen.characters.di

import com.vkondrav.playground.app.screen.characters.usecase.FetchCharactersUseCase
import com.vkondrav.playground.app.screen.characters.usecase.NavigateToCharacterDetailsUseCase
import com.vkondrav.playground.app.screen.characters.viewmodel.CharactersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val charactersModule = module {
    factory {
        FetchCharactersUseCase(
            ramRepository = get(),
        )
    }
    factory {
        NavigateToCharacterDetailsUseCase(
            appState = get(),
        )
    }
    viewModel {
        CharactersViewModel(
            fetchCharactersUseCase = get(),
            navigateToCharacterDetailsUseCase = get(),
            dispatcher = get(),
        )
    }
}
