package com.vkondrav.ram.app.screen.characters.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.vkondrav.ram.common.ui.ComposableItem
import com.vkondrav.ram.app.screen.characters.adapter.CharactersViewItemAdapter
import com.vkondrav.ram.app.screen.characters.usecase.FetchCharactersUseCase
import timber.log.Timber

class CharactersSource(
    private val fetchCharactersUseCase: FetchCharactersUseCase,
    private val charactersViewItemAdapter: CharactersViewItemAdapter,
) : PagingSource<Int, ComposableItem>() {

    override fun getRefreshKey(state: PagingState<Int, ComposableItem>): Int? = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ComposableItem> =
        runCatching {
            val nextPage = params.key ?: 1
            val page = fetchCharactersUseCase(nextPage).getOrThrow()

            LoadResult.Page(
                data = charactersViewItemAdapter(page.items),
                prevKey = page.previousPage,
                nextKey = page.nextPage,
            )

        }.getOrElse { exception ->
            Timber.e(exception)
            LoadResult.Error(exception)
        }

}
