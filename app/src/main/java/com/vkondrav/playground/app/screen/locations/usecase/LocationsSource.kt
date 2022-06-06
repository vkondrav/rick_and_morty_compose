package com.vkondrav.playground.app.screen.locations.usecase

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.vkondrav.playground.app.base.item.ComposableItem
import timber.log.Timber

class LocationsSource(
    private val fetchLocationsUseCase: FetchLocationsUseCase,
    private val locationViewItemsConstructor: LocationViewItemsConstructor,
) : PagingSource<Int, ComposableItem>() {

    override fun getRefreshKey(state: PagingState<Int, ComposableItem>): Int? = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ComposableItem> =
        runCatching {
            val nextPage = params.key ?: 1
            val page = fetchLocationsUseCase(nextPage).getOrThrow()

            LoadResult.Page(
                data = locationViewItemsConstructor(page.items),
                prevKey = page.previousPage,
                nextKey = page.nextPage,
            )

        }.getOrElse { exception ->
            Timber.e(exception)
            LoadResult.Error(exception)
        }

}
