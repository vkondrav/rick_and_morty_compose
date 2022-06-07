package com.vkondrav.playground.app.base.viewmodel

import com.vkondrav.playground.app.base.item.ComposableItem
import com.vkondrav.playground.app.common.composable.PageLoadingViewItem

sealed class ScreenState {
    data class Loading(val item: ComposableItem = PageLoadingViewItem) : ScreenState()
    data class Error(val item: ComposableItem) : ScreenState()
    object Content : ScreenState()
}
