package com.vkondrav.ram.app.screen.characters.di

import com.vkondrav.ram.app.screen.characters.usecase.AddCharacterToFavoritesUseCase
import com.vkondrav.ram.app.screen.characters.usecase.CharactersSource
import com.vkondrav.ram.app.screen.characters.usecase.FetchCharactersUseCase
import com.vkondrav.ram.app.screen.characters.usecase.HandleCharacterFavoritesUseCase
import com.vkondrav.ram.app.screen.characters.usecase.NavigateToCharacterDetailsUseCase
import com.vkondrav.ram.app.screen.characters.usecase.RemoveCharacterFromFavoritesUseCase
import com.vkondrav.ram.app.screen.characters.usecase.CharactersViewItemConstructor
import com.vkondrav.ram.app.screen.characters.viewmodel.CharactersViewModel
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
            navController = get(),
        )
    }
    factory {
        AddCharacterToFavoritesUseCase(
            favoriteCharactersDao = get(),
            snackbarHostState = get(),
            dispatcher = get(),
        )
    }
    factory {
        RemoveCharacterFromFavoritesUseCase(
            favoriteCharactersDao = get(),
            snackbarHostState = get(),
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
        CharactersViewItemConstructor(
            navigateToCharacterDetailsUseCase = get(),
            handleCharacterFavoritesUseCase = get(),
        )
    }
    factory {
        CharactersSource(
            fetchCharactersUseCase = get(),
            charactersViewItemConstructor = get(),
        )
    }
    viewModel {
        CharactersViewModel(
            charactersSource = get(),
            dispatcher = get(),
        )
    }
}
