package com.vkondrav.ram.app.screen.episodes.di

import com.vkondrav.ram.app.screen.episodes.usecase.AddEpisodeToFavoritesUseCase
import com.vkondrav.ram.app.screen.episodes.usecase.FetchEpisodesUseCase
import com.vkondrav.ram.app.screen.episodes.usecase.HandleEpisodeFavoriteUseCase
import com.vkondrav.ram.app.screen.episodes.usecase.NavigateToEpisodeDetailsUseCase
import com.vkondrav.ram.app.screen.episodes.usecase.RemoveEpisodeFromFavoritesUseCase
import com.vkondrav.ram.app.screen.episodes.usecase.EpisodeViewItemsConstructor
import com.vkondrav.ram.app.screen.episodes.usecase.EpisodesSource
import com.vkondrav.ram.app.screen.episodes.viewmodel.EpisodesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val episodesModule = module {
    factory {
        FetchEpisodesUseCase(
            ramRepository = get(),
            favoriteEpisodesDao = get(),
            sourceConstructor = get(),
        )
    }
    factory {
        NavigateToEpisodeDetailsUseCase(
            appState = get(),
        )
    }
    factory {
        RemoveEpisodeFromFavoritesUseCase(
            favoriteEpisodesDao = get(),
            appState = get(),
            dispatcher = get(),
        )
    }
    factory {
        AddEpisodeToFavoritesUseCase(
            favoriteEpisodesDao = get(),
            appState = get(),
            dispatcher = get(),
        )
    }
    factory {
        HandleEpisodeFavoriteUseCase(
            addEpisodeToFavoriteUseCase = get(),
            removeEpisodeFromFavoritesUseCase = get(),
        )
    }
    factory {
        EpisodeViewItemsConstructor(
            navigateToEpisodeDetailsUseCase = get(),
            handleEpisodeFavoriteUseCase = get(),
        )
    }
    factory {
        EpisodesSource(
            fetchEpisodesUseCase = get(),
            episodeViewItemsConstructor = get(),
        )
    }
    viewModel {
        EpisodesViewModel(
            episodesSource = get(),
            dispatcher = get(),
        )
    }
}
