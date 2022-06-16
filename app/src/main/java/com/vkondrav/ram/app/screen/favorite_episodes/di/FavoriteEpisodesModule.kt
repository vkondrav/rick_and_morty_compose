package com.vkondrav.ram.app.screen.favorite_episodes.di

import com.vkondrav.ram.app.screen.favorite_episodes.usecase.FetchFavoriteEpisodesUseCase
import com.vkondrav.ram.app.screen.favorite_episodes.viewmodel.FavoriteEpisodesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteEpisodesModule = module {
    factory {
        FetchFavoriteEpisodesUseCase(
            favoriteEpisodesDao = get(),
            sourceConstructor = get(),
            navigateToLocationDetailsUseCase = get(),
            handleEpisodeFavoriteUseCase = get(),
        )
    }
    viewModel {
        FavoriteEpisodesViewModel(
            fetchFavoriteEpisodesUseCase = get(),
            dispatcher = get(),
        )
    }
}
