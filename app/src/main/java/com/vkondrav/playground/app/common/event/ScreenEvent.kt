package com.vkondrav.playground.app.common.event

import com.vkondrav.playground.app.base.item.ComposableItem

sealed class ScreenEvent(open val item: ComposableItem) {
    data class Loading(override val item: ComposableItem) : ScreenEvent(item)
    data class Content(override val item: ComposableItem) : ScreenEvent(item)
    data class Error(override val item: ComposableItem) : ScreenEvent(item)
}