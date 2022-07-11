package com.vkondrav.ram.episode.all.di

import com.vkondrav.ram.episode.common.usecase.AddEpisodeToFavoritesUseCase
import com.vkondrav.ram.episode.all.usecase.FetchEpisodesUseCase
import com.vkondrav.ram.episode.common.usecase.HandleEpisodeFavoriteUseCase
import com.vkondrav.ram.episode.common.usecase.NavigateToEpisodeDetailsUseCase
import com.vkondrav.ram.episode.common.usecase.RemoveEpisodeFromFavoritesUseCase
import com.vkondrav.ram.episode.common.factory.EpisodeViewItemFactory
import com.vkondrav.ram.episode.all.source.EpisodesSource
import com.vkondrav.ram.episode.all.viewmodel.EpisodesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val episodesModule = module {
    factory {
        FetchEpisodesUseCase(
            ramRepository = get(),
            favoriteEpisodesDao = get(),
            factory = get(),
        )
    }
    factory {
        NavigateToEpisodeDetailsUseCase(
            navigateToRouteUseCase = get(),
        )
    }
    factory {
        RemoveEpisodeFromFavoritesUseCase(
            favoriteEpisodesDao = get(),
            showSnackbarMessageUseCase = get(),
            dispatcher = get(),
        )
    }
    factory {
        AddEpisodeToFavoritesUseCase(
            favoriteEpisodesDao = get(),
            showSnackbarMessageUseCase = get(),
            adapter = get(),
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
        EpisodeViewItemFactory(
            navigateToEpisodeDetailsUseCase = get(),
            handleEpisodeFavoriteUseCase = get(),
        )
    }
    factory {
        EpisodesSource(
            fetchEpisodesUseCase = get(),
            episodeViewItemFactory = get(),
        )
    }
    viewModel {
        EpisodesViewModel(
            episodesSource = get(),
            dispatcher = get(),
        )
    }
}
