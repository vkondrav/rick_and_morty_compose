package com.vkondrav.playground.app.base.viewmodel

import androidx.compose.runtime.MutableState
import com.vkondrav.playground.app.base.item.ComposableItem
import kotlinx.coroutines.flow.Flow

interface ScreenStateViewModel {
    val screenState: MutableState<ScreenState>
    val items: Flow<List<ComposableItem>>
}
