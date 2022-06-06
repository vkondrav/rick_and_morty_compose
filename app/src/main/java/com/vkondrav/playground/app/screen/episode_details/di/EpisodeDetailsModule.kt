package com.vkondrav.playground.app.screen.episode_details.di

import com.vkondrav.playground.app.screen.episode_details.usecase.FetchEpisodeDetailsUseCase
import com.vkondrav.playground.app.screen.episode_details.viewmodel.EpisodeDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val episodeDetailsModule = module {
    factory {
        FetchEpisodeDetailsUseCase(
            ramRepository = get(),
            favoriteCharactersDao = get(),
            favoriteEpisodesDao = get(),
            sourceTransformer = get(),
        )
    }
    viewModel {
        EpisodeDetailsViewModel(
            fetchEpisodeDetailsUseCase = get(),
            dispatcher = get(),
        )
    }
}
