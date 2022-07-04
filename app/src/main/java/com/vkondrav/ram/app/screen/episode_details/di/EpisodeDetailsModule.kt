package com.vkondrav.ram.app.screen.episode_details.di

import com.vkondrav.ram.app.screen.episode_details.usecase.FetchEpisodeDetailsUseCase
import com.vkondrav.ram.app.screen.episode_details.usecase.EpisodeDetailsSource
import com.vkondrav.ram.app.screen.episode_details.viewmodel.EpisodeDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val episodeDetailsModule = module {
    factory {
        FetchEpisodeDetailsUseCase(
            ramRepository = get(),
            favoriteCharactersDao = get(),
            favoriteEpisodesDao = get(),
            adapter = get(),
        )
    }
    factory {
        EpisodeDetailsSource(
            fetchEpisodeDetailsUseCase = get(),
            fetchCollapsableDrawerState = get(),
            handleCollapsableDrawerUseCase = get(),
            charactersViewItemAdapter = get(),
        )
    }
    viewModel { params ->
        EpisodeDetailsViewModel(
            episodeId = params.get(),
            episodeDetailsSource = get(),
            dispatcher = get(),
        )
    }
}
