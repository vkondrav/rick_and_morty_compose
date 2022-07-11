package com.vkondrav.ram.character.all.di

import com.vkondrav.ram.character.common.usecase.AddCharacterToFavoritesUseCase
import com.vkondrav.ram.character.all.source.CharactersSource
import com.vkondrav.ram.character.all.usecase.FetchCharactersUseCase
import com.vkondrav.ram.character.common.usecase.HandleCharacterFavoritesUseCase
import com.vkondrav.ram.character.common.usecase.NavigateToCharacterDetailsUseCase
import com.vkondrav.ram.character.common.usecase.RemoveCharacterFromFavoritesUseCase
import com.vkondrav.ram.character.common.factory.CharacterViewItemFactory
import com.vkondrav.ram.character.all.viewmodel.CharactersViewModel
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
            navigateToRouteUseCase = get(),
        )
    }
    factory {
        AddCharacterToFavoritesUseCase(
            favoriteCharactersDao = get(),
            showSnackbarMessageUseCase = get(),
            adapter = get(),
            dispatcher = get(),
        )
    }
    factory {
        RemoveCharacterFromFavoritesUseCase(
            favoriteCharactersDao = get(),
            showSnackbarMessageUseCase = get(),
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
            characterViewItemFactory = get(),
        )
    }
    viewModel {
        CharactersViewModel(
            charactersSource = get(),
            dispatcher = get(),
        )
    }
}
