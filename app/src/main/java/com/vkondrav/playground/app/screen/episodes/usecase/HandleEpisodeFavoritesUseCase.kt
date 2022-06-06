package com.vkondrav.playground.app.screen.episodes.usecase

import com.vkondrav.playground.domain.RamEpisode

class HandleEpisodeFavoritesUseCase(
    private val addEpisodeToFavoriteUseCase: AddEpisodeToFavoritesUseCase,
    private val removeEpisodeFromFavoritesUseCase: RemoveEpisodeFromFavoritesUseCase,
) {
    operator fun invoke(isFavorite: Boolean, episode: RamEpisode) {
        when (isFavorite) {
            true -> addEpisodeToFavoriteUseCase(episode)
            false -> removeEpisodeFromFavoritesUseCase(episode)
        }
    }
}
