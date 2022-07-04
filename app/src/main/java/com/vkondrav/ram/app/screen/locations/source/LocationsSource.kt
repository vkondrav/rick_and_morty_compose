package com.vkondrav.ram.app.screen.locations.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.vkondrav.ram.app.base.item.ComposableItem
import com.vkondrav.ram.app.screen.locations.adapter.LocationViewItemsAdapter
import com.vkondrav.ram.app.screen.locations.usecase.FetchLocationsUseCase
import timber.log.Timber

class LocationsSource(
    private val fetchLocationsUseCase: FetchLocationsUseCase,
    private val locationViewItemsAdapter: LocationViewItemsAdapter,
) : PagingSource<Int, ComposableItem>() {

    override fun getRefreshKey(state: PagingState<Int, ComposableItem>): Int? = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ComposableItem> =
        runCatching {
            val nextPage = params.key ?: 1
            val page = fetchLocationsUseCase(nextPage).getOrThrow()

            LoadResult.Page(
                data = locationViewItemsAdapter(page.items),
                prevKey = page.previousPage,
                nextKey = page.nextPage,
            )

        }.getOrElse { exception ->
            Timber.e(exception)
            LoadResult.Error(exception)
        }

}
