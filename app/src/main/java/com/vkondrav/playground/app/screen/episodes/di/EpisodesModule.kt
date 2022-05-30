package com.vkondrav.playground.app.screen.episodes.di

import com.vkondrav.playground.app.screen.episodes.usecase.FetchEpisodesUseCase
import com.vkondrav.playground.app.screen.episodes.usecase.NavigateToEpisodeDetailsUseCase
import com.vkondrav.playground.app.screen.episodes.viewmodel.EpisodesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val episodesModule = module {
    factory {
        FetchEpisodesUseCase(
            ramRepository = get(),
        )
    }
    factory {
        NavigateToEpisodeDetailsUseCase(
            appState = get(),
        )
    }
    viewModel {
        EpisodesViewModel(
            fetchEpisodesUseCase = get(),
            navigateToEpisodeDetailsUseCase = get(),
            dispatcher = get(),
        )
    }
}
