package com.vkondrav.playground.app.base.viewmodel

import androidx.compose.runtime.State
import com.vkondrav.playground.app.base.item.ComposableItem
import kotlinx.coroutines.flow.StateFlow

interface ScreenStateViewModel {
    val screenState: State<ScreenState>
    val items: StateFlow<List<ComposableItem>>
}
