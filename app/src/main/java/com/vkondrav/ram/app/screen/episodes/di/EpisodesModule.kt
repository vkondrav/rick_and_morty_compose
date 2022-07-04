package com.vkondrav.ram.app.screen.episodes.di

import com.vkondrav.ram.app.screen.episodes.usecase.AddEpisodeToFavoritesUseCase
import com.vkondrav.ram.app.screen.episodes.usecase.FetchEpisodesUseCase
import com.vkondrav.ram.app.screen.episodes.usecase.HandleEpisodeFavoriteUseCase
import com.vkondrav.ram.app.screen.episodes.usecase.NavigateToEpisodeDetailsUseCase
import com.vkondrav.ram.app.screen.episodes.usecase.RemoveEpisodeFromFavoritesUseCase
import com.vkondrav.ram.app.screen.episodes.adapter.EpisodeViewItemsAdapter
import com.vkondrav.ram.app.screen.episodes.source.EpisodesSource
import com.vkondrav.ram.app.screen.episodes.viewmodel.EpisodesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val episodesModule = module {
    factory {
        FetchEpisodesUseCase(
            ramRepository = get(),
            favoriteEpisodesDao = get(),
            adapter = get(),
        )
    }
    factory {
        NavigateToEpisodeDetailsUseCase(
            navigationStateHolder = get(),
        )
    }
    factory {
        RemoveEpisodeFromFavoritesUseCase(
            favoriteEpisodesDao = get(),
            snackbarController = get(),
            dispatcher = get(),
        )
    }
    factory {
        AddEpisodeToFavoritesUseCase(
            favoriteEpisodesDao = get(),
            snackbarController = get(),
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
        EpisodeViewItemsAdapter(
            navigateToEpisodeDetailsUseCase = get(),
            handleEpisodeFavoriteUseCase = get(),
        )
    }
    factory {
        EpisodesSource(
            fetchEpisodesUseCase = get(),
            episodeViewItemsAdapter = get(),
        )
    }
    viewModel {
        EpisodesViewModel(
            episodesSource = get(),
            dispatcher = get(),
        )
    }
}
