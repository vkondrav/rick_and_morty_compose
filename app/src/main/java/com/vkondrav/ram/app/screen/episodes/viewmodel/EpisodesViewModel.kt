package com.vkondrav.ram.app.screen.episodes.viewmodel

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.vkondrav.ram.app.base.item.ComposableItem
import com.vkondrav.ram.app.base.viewmodel.BaseViewModel
import com.vkondrav.ram.app.base.viewmodel.PagingViewModel
import com.vkondrav.ram.app.screen.episodes.source.EpisodesSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

class EpisodesViewModel(
    private val episodesSource: EpisodesSource,
    dispatcher: CoroutineDispatcher,
) : BaseViewModel(dispatcher), PagingViewModel {

    override val pagingData: Flow<PagingData<ComposableItem>> = Pager(PagingConfig(pageSize = 1)) {
        episodesSource
    }.flow.cachedIn(this)
}
