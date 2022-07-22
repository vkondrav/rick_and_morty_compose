package com.vkondrav.ram.location.all.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.vkondrav.ram.common.ui.view.ComposableItem
import com.vkondrav.ram.location.all.usecase.FetchLocationsUseCase
import com.vkondrav.ram.location.common.factory.LocationViewItemFactory
import timber.log.Timber

class LocationsSource(
    private val fetchLocationsUseCase: FetchLocationsUseCase,
    private val locationViewItemFactory: LocationViewItemFactory,
) : PagingSource<Int, ComposableItem>() {

    override fun getRefreshKey(state: PagingState<Int, ComposableItem>): Int? = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ComposableItem> =
        runCatching {
            val nextPage = params.key ?: 1
            val page = fetchLocationsUseCase(nextPage).getOrThrow()

            LoadResult.Page(
                data = locationViewItemFactory(page.items),
                prevKey = page.previousPage,
                nextKey = page.nextPage,
            )
        }.getOrElse { exception ->
            Timber.e(exception)
            LoadResult.Error(exception)
        }
}
