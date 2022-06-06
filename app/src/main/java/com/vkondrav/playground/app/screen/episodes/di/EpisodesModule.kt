package com.vkondrav.playground.app.screen.episodes.di

import com.vkondrav.playground.app.screen.episodes.usecase.AddEpisodeToFavoritesUseCase
import com.vkondrav.playground.app.screen.episodes.usecase.FetchEpisodesUseCase
import com.vkondrav.playground.app.screen.episodes.usecase.HandleEpisodeFavoritesUseCase
import com.vkondrav.playground.app.screen.episodes.usecase.NavigateToEpisodeDetailsUseCase
import com.vkondrav.playground.app.screen.episodes.usecase.RemoveEpisodeFromFavoritesUseCase
import com.vkondrav.playground.app.screen.episodes.usecase.TransformEpisodesUseCase
import com.vkondrav.playground.app.screen.episodes.viewmodel.EpisodesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val episodesModule = module {
    factory {
        FetchEpisodesUseCase(
            ramRepository = get(),
            favoriteEpisodesDao = get(),
            sourceTransformer = get(),
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
        HandleEpisodeFavoritesUseCase(
            addEpisodeToFavoriteUseCase = get(),
            removeEpisodeFromFavoritesUseCase = get(),
        )
    }
    factory {
        TransformEpisodesUseCase(
            navigateToEpisodeDetailsUseCase = get(),
            handleEpisodeFavoritesUseCase = get(),
        )
    }
    viewModel {
        EpisodesViewModel(
            fetchEpisodesUseCase = get(),
            transformEpisodesUseCase = get(),
            dispatcher = get(),
        )
    }
}
