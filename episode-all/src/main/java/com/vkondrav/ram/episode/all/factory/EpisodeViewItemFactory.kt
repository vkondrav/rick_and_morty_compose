package com.vkondrav.ram.episode.all.factory

import com.vkondrav.ram.common.ui.view.ComposableItem
import com.vkondrav.ram.episode.all.composable.EpisodeViewItem
import com.vkondrav.ram.episode.all.usecase.HandleEpisodeFavoriteUseCase
import com.vkondrav.ram.episode.all.usecase.NavigateToEpisodeDetailsUseCase
import com.vkondrav.ram.domain.RamEpisode

class EpisodeViewItemFactory(
    private val navigateToEpisodeDetailsUseCase: NavigateToEpisodeDetailsUseCase,
    private val handleEpisodeFavoriteUseCase: HandleEpisodeFavoriteUseCase,
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
                handleEpisodeFavoriteUseCase(this, isFavorite)
            },
        )
}
