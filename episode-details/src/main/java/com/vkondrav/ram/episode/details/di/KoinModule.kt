package com.vkondrav.ram.episode.details.di

import com.vkondrav.ram.episode.details.usecase.EpisodeDetailsSource
import com.vkondrav.ram.episode.details.usecase.FetchEpisodeDetailsUseCase
import com.vkondrav.ram.episode.details.viewmodel.EpisodeDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val episodeDetailsModule = module {
    factory {
        FetchEpisodeDetailsUseCase(
            ramRepository = get(),
            favoriteCharactersDao = get(),
            favoriteEpisodesDao = get(),
            factory = get(),
        )
    }
    factory {
        EpisodeDetailsSource(
            fetchEpisodeDetailsUseCase = get(),
            fetchCollapsableDrawerState = get(),
            handleCollapsableDrawerUseCase = get(),
            characterViewItemFactory = get(),
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
