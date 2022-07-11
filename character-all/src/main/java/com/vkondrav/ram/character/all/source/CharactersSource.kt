package com.vkondrav.ram.character.all.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.vkondrav.ram.common.ui.view.ComposableItem
import com.vkondrav.ram.character.common.factory.CharacterViewItemFactory
import com.vkondrav.ram.character.all.usecase.FetchCharactersUseCase
import timber.log.Timber

class CharactersSource(
    private val fetchCharactersUseCase: FetchCharactersUseCase,
    private val characterViewItemFactory: CharacterViewItemFactory,
) : PagingSource<Int, ComposableItem>() {

    override fun getRefreshKey(state: PagingState<Int, ComposableItem>): Int? = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ComposableItem> =
        runCatching {
            val nextPage = params.key ?: 1
            val page = fetchCharactersUseCase(nextPage).getOrThrow()

            LoadResult.Page(
                data = characterViewItemFactory(page.items),
                prevKey = page.previousPage,
                nextKey = page.nextPage,
            )

        }.getOrElse { exception ->
            Timber.e(exception)
            LoadResult.Error(exception)
        }

}
