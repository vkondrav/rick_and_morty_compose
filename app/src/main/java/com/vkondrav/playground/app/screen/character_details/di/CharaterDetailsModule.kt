package com.vkondrav.playground.app.screen.character_details.di

import com.vkondrav.playground.app.screen.character_details.usecase.FetchCharacterDetailsUseCase
import com.vkondrav.playground.app.screen.character_details.usecase.CharacterDetailsSource
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
            sourceConstructor = get(),
        )
    }
    factory {
        CharacterDetailsSource(
            fetchCharacterDetailsUseCase = get(),
            fetchCollapsableDrawerState = get(),
            handleCollapsableDrawerUseCase = get(),
            locationViewItemsConstructor = get(),
            episodeViewItemsConstructor = get(),
        )
    }
    viewModel { params ->
        CharacterDetailsViewModel(
            characterId = params.get(),
            characterDetailsSource = get(),
            dispatcher = get(),
        )
    }
}
