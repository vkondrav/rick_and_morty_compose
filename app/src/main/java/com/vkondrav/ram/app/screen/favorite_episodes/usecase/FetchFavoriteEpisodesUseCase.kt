package com.vkondrav.ram.app.screen.favorite_episodes.usecase

import com.vkondrav.ram.common.ui.ComposableItem
import com.vkondrav.ram.app.screen.episodes.composable.EpisodeViewItem
import com.vkondrav.ram.app.screen.episodes.usecase.HandleEpisodeFavoriteUseCase
import com.vkondrav.ram.app.screen.episodes.usecase.NavigateToEpisodeDetailsUseCase
import com.vkondrav.ram.domain.RamEpisode
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
