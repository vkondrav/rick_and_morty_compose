package com.vkondrav.playground.app.screen.episodes.usecase

import com.vkondrav.playground.domain.RamEpisode

class HandleEpisodeFavoriteUseCase(
    private val addEpisodeToFavoriteUseCase: AddEpisodeToFavoritesUseCase,
    private val removeEpisodeFromFavoritesUseCase: RemoveEpisodeFromFavoritesUseCase,
) {
    operator fun invoke(episode: RamEpisode, isFavorite: Boolean) {
        when (isFavorite) {
            true -> addEpisodeToFavoriteUseCase(episode)
            false -> removeEpisodeFromFavoritesUseCase(episode)
        }
    }
}
