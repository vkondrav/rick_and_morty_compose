package com.vkondrav.playground.app.screen.episodes.usecase

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.vkondrav.playground.app.base.item.ComposableItem
import timber.log.Timber

class EpisodesSource(
    private val fetchEpisodesUseCase: FetchEpisodesUseCase,
    private val transformEpisodesUseCase: TransformEpisodesUseCase,
) : PagingSource<Int, ComposableItem>() {

    override fun getRefreshKey(state: PagingState<Int, ComposableItem>): Int? = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ComposableItem> =
        runCatching {
            val nextPage = params.key ?: 0
            val page = fetchEpisodesUseCase(nextPage).getOrThrow()

            LoadResult.Page(
                data = transformEpisodesUseCase(page.items),
                prevKey = page.previousPage,
                nextKey = page.nextPage,
            )

        }.getOrElse { exception ->
            Timber.e(exception)
            LoadResult.Error(exception)
        }

}