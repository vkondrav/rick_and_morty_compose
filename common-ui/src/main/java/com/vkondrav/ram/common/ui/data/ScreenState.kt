package com.vkondrav.ram.common.ui.data

import com.vkondrav.ram.common.ui.view.ComposableItem
import com.vkondrav.ram.common.ui.view.PageLoadingViewItem

sealed class ScreenState {
    data class Loading(val item: ComposableItem = PageLoadingViewItem) : ScreenState()
    data class Error(val item: ComposableItem) : ScreenState()
    object Content : ScreenState()
}
