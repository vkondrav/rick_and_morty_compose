package com.vkondrav.playground.app.screen.characters.usecase

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.vkondrav.playground.app.base.item.ComposableItem
import timber.log.Timber

class CharactersSource(
    private val fetchCharactersUseCase: FetchCharactersUseCase,
    private val transformCharactersUseCase: TransformCharactersUseCase,
) : PagingSource<Int, ComposableItem>() {

    override fun getRefreshKey(state: PagingState<Int, ComposableItem>): Int? = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ComposableItem> =
        runCatching {
            val nextPage = params.key ?: 1
            val page = fetchCharactersUseCase(nextPage).getOrThrow()

            LoadResult.Page(
                data = transformCharactersUseCase(page.items),
                prevKey = page.previousPage,
                nextKey = page.nextPage,
            )

        }.getOrElse { exception ->
            Timber.e(exception)
            LoadResult.Error(exception)
        }

}
