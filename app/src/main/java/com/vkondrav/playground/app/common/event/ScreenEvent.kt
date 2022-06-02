package com.vkondrav.playground.app.common.event

import com.vkondrav.playground.app.base.item.ComposableItem
import com.vkondrav.playground.app.common.composable.PageLoadingViewItem

sealed class ScreenEvent(open val item: ComposableItem) {
    data class Loading(override val item: ComposableItem = PageLoadingViewItem) : ScreenEvent(item)
    data class Content(override val item: ComposableItem) : ScreenEvent(item)
    data class Error(override val item: ComposableItem) : ScreenEvent(item)
}
