package com.vkondrav.playground.app.screen.character_details.di

import com.vkondrav.playground.app.screen.character_details.usecase.FetchCharacterDetailsUseCase
import com.vkondrav.playground.app.screen.character_details.usecase.TransformCharacterDetailsUseCase
import com.vkondrav.playground.app.screen.character_details.viewmodel.CharacterDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val characterDetailsModule = module {
    factory {
        FetchCharacterDetailsUseCase(
            ramRepository = get(),
            favoriteCharactersDao = get(),
            favoriteLocationsDao = get(),
            favoriteEpisodesDao = get(),
            transformer = get(),
        )
    }
    factory {
        TransformCharacterDetailsUseCase(
            transformLocationsUseCase = get(),
            transformEpisodesUseCase = get(),
        )
    }
    viewModel { params ->
        CharacterDetailsViewModel(
            characterId = params.get(),
            fetchCharacterDetailsUseCase = get(),
            transformCharacterDetailsUseCase = get(),
            dispatcher = get(),
        )
    }
}
