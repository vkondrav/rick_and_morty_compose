package com.vkondrav.ram.app.screen.episodes.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.vkondrav.ram.common.ui.view.ComposableItem
import com.vkondrav.ram.app.screen.episodes.factory.EpisodeViewItemFactory
import com.vkondrav.ram.app.screen.episodes.usecase.FetchEpisodesUseCase
import timber.log.Timber

class EpisodesSource(
    private val fetchEpisodesUseCase: FetchEpisodesUseCase,
    private val episodeViewItemFactory: EpisodeViewItemFactory,
) : PagingSource<Int, ComposableItem>() {

    override fun getRefreshKey(state: PagingState<Int, ComposableItem>): Int? = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ComposableItem> =
        runCatching {
            val nextPage = params.key ?: 1
            val page = fetchEpisodesUseCase(nextPage).getOrThrow()

            LoadResult.Page(
                data = episodeViewItemFactory(page.items),
                prevKey = page.previousPage,
                nextKey = page.nextPage,
            )

        }.getOrElse { exception ->
            Timber.e(exception)
            LoadResult.Error(exception)
        }

}
