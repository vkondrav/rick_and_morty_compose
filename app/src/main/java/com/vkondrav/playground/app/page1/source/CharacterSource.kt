package com.vkondrav.playground.app.page1.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.vkondrav.playground.app.base.item.ComposableItem
import com.vkondrav.playground.app.characters.composable.CharacterViewItem
import com.vkondrav.playground.graphql.ram.RamRepository

class CharacterSource(
    private val ramRepository: RamRepository,
) : PagingSource<Int, ComposableItem>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ComposableItem> {
        val nextPage = params.key ?: 1
        val result = ramRepository.fetchCharacters(nextPage)

        return result.getOrElse { error ->
            return LoadResult.Error(error)
        }.let { value ->
            LoadResult.Page(
                data = value.map { CharacterViewItem(it) },
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = nextPage.plus(1)
            )
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ComposableItem>): Int? = null
}