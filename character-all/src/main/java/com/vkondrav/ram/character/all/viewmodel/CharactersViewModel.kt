package com.vkondrav.ram.character.all.viewmodel

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.vkondrav.ram.character.all.source.CharactersSource
import com.vkondrav.ram.common.ui.view.ComposableItem
import com.vkondrav.ram.common.ui.viewmodel.BaseViewModel
import com.vkondrav.ram.common.ui.viewmodel.PagingViewModel
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
