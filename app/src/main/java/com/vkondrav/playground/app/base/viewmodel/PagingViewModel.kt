package com.vkondrav.playground.app.base.viewmodel

import androidx.paging.PagingData
import com.vkondrav.playground.app.base.item.ComposableItem
import kotlinx.coroutines.flow.Flow

interface PagingViewModel {
    val pagingData: Flow<PagingData<ComposableItem>>
}
