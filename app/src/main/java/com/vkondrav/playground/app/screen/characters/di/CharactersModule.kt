package com.vkondrav.playground.app.screen.characters.di

import com.vkondrav.playground.app.screen.characters.usecase.AddCharacterToFavoritesUseCase
import com.vkondrav.playground.app.screen.characters.usecase.FetchCharactersUseCase
import com.vkondrav.playground.app.screen.characters.usecase.NavigateToCharacterDetailsUseCase
import com.vkondrav.playground.app.screen.characters.usecase.RemoveCharacterFromFavoritesUseCase
import com.vkondrav.playground.app.screen.characters.usecase.TransformCharactersUseCase
import com.vkondrav.playground.app.screen.characters.viewmodel.CharactersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val charactersModule = module {
    factory {
        FetchCharactersUseCase(
            ramRepository = get(),
            favoritesDao = get(),
        )
    }
    factory {
        NavigateToCharacterDetailsUseCase(
            appState = get(),
        )
    }
    factory {
        AddCharacterToFavoritesUseCase(
            favoritesDao = get(),
            dispatcher = get(),
        )
    }
    factory {
        RemoveCharacterFromFavoritesUseCase(
            favoritesDao = get(),
            dispatcher = get(),
        )
    }
    factory {
        TransformCharactersUseCase(
            navigateToCharacterDetailsUseCase = get(),
            addCharacterToFavoritesUseCase = get(),
            removeCharacterFromFavoritesUseCase = get(),
        )
    }
    viewModel {
        CharactersViewModel(
            fetchCharactersUseCase = get(),
            transformCharactersUseCase = get(),
            dispatcher = get(),
        )
    }
}
