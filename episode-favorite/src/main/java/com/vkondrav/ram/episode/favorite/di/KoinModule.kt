package com.vkondrav.ram.episode.favorite.di

import com.vkondrav.ram.episode.favorite.usecase.FetchFavoriteEpisodesUseCase
import com.vkondrav.ram.episode.favorite.viewmodel.FavoriteEpisodesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteEpisodesModule = module {
    factory {
        FetchFavoriteEpisodesUseCase(
            favoriteEpisodesDao = get(),
            factory = get(),
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
