package com.vkondrav.ram.character.favorite.di

import com.vkondrav.ram.character.favorite.usecase.FetchFavoriteCharactersUseCase
import com.vkondrav.ram.character.favorite.viewmodel.FavoriteCharactersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteCharactersModule = module {
    factory {
        FetchFavoriteCharactersUseCase(
            favoriteCharactersDao = get(),
            factory = get(),
            navigateToCharacterDetailsUseCase = get(),
            handleCharacterFavoritesUseCase = get(),
        )
    }
    viewModel {
        FavoriteCharactersViewModel(
            favoriteCharactersUseCase = get(),
            dispatcher = get(),
        )
    }
}
