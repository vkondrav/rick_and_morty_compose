package com.vkondrav.playground.app.screen.characters.di

import com.vkondrav.playground.app.screen.characters.usecase.AddCharacterToFavoritesUseCase
import com.vkondrav.playground.app.screen.characters.usecase.CharactersSource
import com.vkondrav.playground.app.screen.characters.usecase.FetchCharactersUseCase
import com.vkondrav.playground.app.screen.characters.usecase.HandleCharacterFavoritesUseCase
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
            favoriteCharactersDao = get(),
            transformer = get(),
        )
    }
    factory {
        NavigateToCharacterDetailsUseCase(
            appState = get(),
        )
    }
    factory {
        AddCharacterToFavoritesUseCase(
            favoriteCharactersDao = get(),
            appState = get(),
            dispatcher = get(),
        )
    }
    factory {
        RemoveCharacterFromFavoritesUseCase(
            favoriteCharactersDao = get(),
            appState = get(),
            dispatcher = get(),
        )
    }
    factory {
        HandleCharacterFavoritesUseCase(
            addCharacterToFavoritesUseCase = get(),
            removeCharacterFromFavoritesUseCase = get(),
        )
    }
    factory {
        TransformCharactersUseCase(
            navigateToCharacterDetailsUseCase = get(),
            handleCharacterFavoritesUseCase = get(),
        )
    }
    factory {
        CharactersSource(
            fetchCharactersUseCase = get(),
            transformCharactersUseCase = get(),
        )
    }
    viewModel {
        CharactersViewModel(
            charactersSource = get(),
            dispatcher = get(),
        )
    }
}
