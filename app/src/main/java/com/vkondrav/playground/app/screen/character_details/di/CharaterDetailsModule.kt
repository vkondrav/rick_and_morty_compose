package com.vkondrav.playground.app.screen.character_details.di

import com.vkondrav.playground.app.screen.character_details.usecase.FetchCharacterDetailsUseCase
import com.vkondrav.playground.app.screen.character_details.usecase.TransformCharacterDetailsUseCase
import com.vkondrav.playground.app.screen.character_details.viewmodel.CharacterDetailsViewModel
import com.vkondrav.playground.graphql.ram.domain.RamCharacterDetailsTransformer
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val characterDetailsModule = module {
    factory {
        RamCharacterDetailsTransformer
    }
    factory {
        FetchCharacterDetailsUseCase(
            ramRepository = get(),
            favoriteLocationsDao = get(),
            ramCharacterDetailsTransformer = get(),
        )
    }
    factory {
        TransformCharacterDetailsUseCase(
            navigateToEpisodeDetailsUseCase = get(),
            navigateToLocationDetailsUseCase = get(),
            handleLocationFavoriteUseCase = get(),
        )
    }
    viewModel {
        CharacterDetailsViewModel(
            fetchCharacterDetailsUseCase = get(),
            transformCharacterDetailsUseCase = get(),
            dispatcher = get(),
        )
    }
}
