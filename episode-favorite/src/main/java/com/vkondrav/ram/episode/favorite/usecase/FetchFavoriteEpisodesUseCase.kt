package com.vkondrav.ram.episode.favorite.usecase

import com.vkondrav.ram.common.ui.view.ComposableItem
import com.vkondrav.ram.domain.RamEpisode
import com.vkondrav.ram.episode.common.composable.EpisodeViewItem
import com.vkondrav.ram.episode.common.usecase.HandleEpisodeFavoriteUseCase
import com.vkondrav.ram.episode.common.usecase.NavigateToEpisodeDetailsUseCase
import com.vkondrav.ram.room.FavoriteEpisode
import com.vkondrav.ram.room.FavoriteEpisodesDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class FetchFavoriteEpisodesUseCase(
    private val favoriteEpisodesDao: FavoriteEpisodesDao,
    private val factory: RamEpisode.Factory,
    private val navigateToLocationDetailsUseCase: NavigateToEpisodeDetailsUseCase,
    private val handleEpisodeFavoriteUseCase: HandleEpisodeFavoriteUseCase,
) {

    operator fun invoke(): Result<Flow<List<ComposableItem>>> = runCatching {
        favoriteEpisodesDao.getAll().map { favoriteEpisodes ->
            favoriteEpisodes.map { it.viewItem }
        }
    }

    private val FavoriteEpisode.viewItem
        get() = with(factory(this, flowOf(setOf(id)))) {
            EpisodeViewItem(
                episode = this,
                onClickAction = {
                    navigateToLocationDetailsUseCase(
                        id,
                        title,
                    )
                },
                onFavoriteAction = { isFavorite ->
                    handleEpisodeFavoriteUseCase(this, isFavorite)
                },
            )
        }
}
