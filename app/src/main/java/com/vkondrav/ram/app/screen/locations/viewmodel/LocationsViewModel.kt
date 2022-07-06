package com.vkondrav.ram.app.screen.locations.viewmodel

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.vkondrav.ram.common.ui.ComposableItem
import com.vkondrav.ram.common.ui.BaseViewModel
import com.vkondrav.ram.common.ui.PagingViewModel
import com.vkondrav.ram.app.screen.locations.source.LocationsSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

class LocationsViewModel(
    private val locationsSource: LocationsSource,
    dispatcher: CoroutineDispatcher,
) : BaseViewModel(dispatcher), PagingViewModel {

    override val pagingData: Flow<PagingData<ComposableItem>> = Pager(PagingConfig(pageSize = 1)) {
        locationsSource
    }.flow.cachedIn(this)
}
