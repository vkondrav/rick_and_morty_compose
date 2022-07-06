package com.vkondrav.ram.common.ui

sealed class ScreenState {
    data class Loading(val item: ComposableItem = PageLoadingViewItem) : ScreenState()
    data class Error(val item: ComposableItem) : ScreenState()
    object Content : ScreenState()
}
