package com.vkondrav.playground.app.screen.episodes.usecase

import com.vkondrav.playground.app.base.item.ComposableItem
import com.vkondrav.playground.app.screen.episodes.composable.EpisodeViewItem
import com.vkondrav.playground.graphql.ram.domain.RamEpisode

class TransformEpisodesUseCase(
    private val navigateToEpisodeDetailsUseCase: NavigateToEpisodeDetailsUseCase,
    private val handleEpisodeFavoritesUseCase: HandleEpisodeFavoritesUseCase,
) {

    operator fun invoke(episode: RamEpisode): ComposableItem = episode.viewItem

    operator fun invoke(episodes: List<RamEpisode>): List<ComposableItem> =
        episodes.map { it.viewItem }

    private val RamEpisode.viewItem
        get() = EpisodeViewItem(
            episode = this,
            onClickAction = {
                navigateToEpisodeDetailsUseCase(
                    id,
                    title,
                )
            },
            onFavoriteAction = { isFavorite ->
                handleEpisodeFavoritesUseCase(isFavorite, episode = this)
            },
        )
}
