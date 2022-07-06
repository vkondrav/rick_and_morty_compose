package com.vkondrav.ram.app.screen.episodes.adapter

import com.vkondrav.ram.common.ui.ComposableItem
import com.vkondrav.ram.app.screen.episodes.composable.EpisodeViewItem
import com.vkondrav.ram.app.screen.episodes.usecase.HandleEpisodeFavoriteUseCase
import com.vkondrav.ram.app.screen.episodes.usecase.NavigateToEpisodeDetailsUseCase
import com.vkondrav.ram.domain.RamEpisode

class EpisodeViewItemsAdapter(
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
