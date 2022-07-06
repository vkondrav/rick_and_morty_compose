package com.vkondrav.ram.app.screen.characters.di

import com.vkondrav.ram.app.screen.characters.usecase.AddCharacterToFavoritesUseCase
import com.vkondrav.ram.app.screen.characters.source.CharactersSource
import com.vkondrav.ram.app.screen.characters.usecase.FetchCharactersUseCase
import com.vkondrav.ram.app.screen.characters.usecase.HandleCharacterFavoritesUseCase
import com.vkondrav.ram.app.screen.characters.usecase.NavigateToCharacterDetailsUseCase
import com.vkondrav.ram.app.screen.characters.usecase.RemoveCharacterFromFavoritesUseCase
import com.vkondrav.ram.app.screen.characters.factory.CharacterViewItemFactory
import com.vkondrav.ram.app.screen.characters.viewmodel.CharactersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val charactersModule = module {
    factory {
        FetchCharactersUseCase(
            ramRepository = get(),
            favoriteCharactersDao = get(),
            factory = get(),
        )
    }
    factory {
        NavigateToCharacterDetailsUseCase(
            navigator = get(),
        )
    }
    factory {
        AddCharacterToFavoritesUseCase(
            favoriteCharactersDao = get(),
            snackbarController = get(),
            adapter = get(),
            dispatcher = get(),
        )
    }
    factory {
        RemoveCharacterFromFavoritesUseCase(
            favoriteCharactersDao = get(),
            snackbarController = get(),
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
        CharacterViewItemFactory(
            navigateToCharacterDetailsUseCase = get(),
            handleCharacterFavoritesUseCase = get(),
        )
    }
    factory {
        CharactersSource(
            fetchCharactersUseCase = get(),
            charactersViewItemAdapter = get(),
        )
    }
    viewModel {
        CharactersViewModel(
            charactersSource = get(),
            dispatcher = get(),
        )
    }
}
