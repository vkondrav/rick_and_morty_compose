package com.vkondrav.ram.app.screen.characters.viewmodel

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.vkondrav.ram.common.ui.PagingViewModel
import com.vkondrav.ram.common.ui.ComposableItem
import com.vkondrav.ram.common.ui.BaseViewModel
import com.vkondrav.ram.app.screen.characters.source.CharactersSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

class CharactersViewModel(
    private val charactersSource: CharactersSource,
    dispatcher: CoroutineDispatcher,
) : BaseViewModel(dispatcher), PagingViewModel {

    override val pagingData: Flow<PagingData<ComposableItem>> = Pager(PagingConfig(pageSize = 1)) {
        charactersSource
    }.flow.cachedIn(this)
}
