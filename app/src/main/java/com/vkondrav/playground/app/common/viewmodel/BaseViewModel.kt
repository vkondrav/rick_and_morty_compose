package com.vkondrav.playground.app.common.viewmodel

import androidx.lifecycle.ViewModel
import com.vkondrav.playground.app.common.item.ComposableAction
import com.vkondrav.playground.app.common.item.ComposableItem

abstract class BaseViewModel : ViewModel() {
    abstract val columnItems: List<ComposableItem>
    abstract fun fetchData()
    abstract fun onAction(action: ComposableAction)
}