package com.vkondrav.ram.common.ui.viewmodel

import androidx.compose.runtime.State
import com.vkondrav.ram.common.ui.data.ScreenState
import com.vkondrav.ram.common.ui.view.ComposableItem
import kotlinx.coroutines.flow.StateFlow

interface ScreenStateViewModel {
    val screenState: State<ScreenState>
    val items: StateFlow<List<ComposableItem>>
}
