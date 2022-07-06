package com.vkondrav.ram.common.ui

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface PagingViewModel {
    val pagingData: Flow<PagingData<ComposableItem>>
}
