package com.vkondrav.ram.common.ui

import androidx.compose.runtime.State
import kotlinx.coroutines.flow.StateFlow

interface ScreenStateViewModel {
    val screenState: State<ScreenState>
    val items: StateFlow<List<ComposableItem>>
}
