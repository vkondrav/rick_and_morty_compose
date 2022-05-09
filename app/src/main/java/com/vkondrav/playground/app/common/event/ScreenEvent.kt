package com.vkondrav.playground.app.common.event

import com.vkondrav.playground.app.base.item.ComposableItem

sealed class ScreenEvent {
    object None : ScreenEvent()
    data class Loading(val item: ComposableItem) : ScreenEvent()
    data class Column(val items: List<ComposableItem>) : ScreenEvent()
    data class Error(val item: ComposableItem) : ScreenEvent()
}