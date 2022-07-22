package com.vkondrav.ram.character.details.di

import com.vkondrav.ram.character.details.usecase.CharacterDetailsSource
import com.vkondrav.ram.character.details.usecase.FetchCharacterDetailsUseCase
import com.vkondrav.ram.character.details.viewmodel.CharacterDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val characterDetailsModule = module {
    factory {
        FetchCharacterDetailsUseCase(
            ramRepository = get(),
            favoriteCharactersDao = get(),
            favoriteLocationsDao = get(),
            favoriteEpisodesDao = get(),
            factory = get(),
        )
    }
    factory {
        CharacterDetailsSource(
            fetchCharacterDetailsUseCase = get(),
            fetchCollapsableDrawerState = get(),
            handleCollapsableDrawerUseCase = get(),
            locationViewItemFactory = get(),
            episodeViewItemFactory = get(),
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
