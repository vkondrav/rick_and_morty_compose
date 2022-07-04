package com.vkondrav.ram.app.screen.favorite_characters.di

import com.vkondrav.ram.app.screen.favorite_characters.usecase.FetchFavoriteCharactersUseCase
import com.vkondrav.ram.app.screen.favorite_characters.viewmodel.FavoriteCharactersViewModel
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
