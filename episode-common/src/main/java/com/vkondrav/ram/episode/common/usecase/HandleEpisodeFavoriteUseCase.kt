package com.vkondrav.ram.episode.common.usecase

import com.vkondrav.ram.domain.RamEpisode

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
