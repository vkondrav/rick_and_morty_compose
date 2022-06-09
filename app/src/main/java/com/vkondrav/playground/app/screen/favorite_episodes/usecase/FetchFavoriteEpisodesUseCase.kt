package com.vkondrav.playground.app.screen.favorite_episodes.usecase

import com.vkondrav.playground.app.base.item.ComposableItem
import com.vkondrav.playground.app.screen.episodes.composable.EpisodeViewItem
import com.vkondrav.playground.app.screen.episodes.usecase.HandleEpisodeFavoriteUseCase
import com.vkondrav.playground.app.screen.episodes.usecase.NavigateToEpisodeDetailsUseCase
import com.vkondrav.playground.domain.RamEpisode
import com.vkondrav.playground.room.ram.FavoriteEpisode
import com.vkondrav.playground.room.ram.FavoriteEpisodesDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class FetchFavoriteEpisodesUseCase(
    private val favoriteEpisodesDao: FavoriteEpisodesDao,
    private val sourceConstructor: RamEpisode.SourceConstructor,
    private val navigateToLocationDetailsUseCase: NavigateToEpisodeDetailsUseCase,
    private val handleEpisodeFavoriteUseCase: HandleEpisodeFavoriteUseCase,
) {

    operator fun invoke(): Result<Flow<List<ComposableItem>>> = runCatching {
        favoriteEpisodesDao.getAll().map { favoriteEpisodes ->
            favoriteEpisodes.map { it.viewItem }
        }
    }

    private val FavoriteEpisode.viewItem
        get() = with(sourceConstructor(this, flowOf(setOf(id)))) {
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
