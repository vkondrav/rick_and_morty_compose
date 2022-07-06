package com.vkondrav.ram.common.ui.viewmodel

import androidx.paging.PagingData
import com.vkondrav.ram.common.ui.view.ComposableItem
import kotlinx.coroutines.flow.Flow

interface PagingViewModel {
    val pagingData: Flow<PagingData<ComposableItem>>
}
