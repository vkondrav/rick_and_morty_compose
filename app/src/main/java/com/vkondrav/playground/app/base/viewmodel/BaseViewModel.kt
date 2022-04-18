package com.vkondrav.playground.app.base.viewmodel

import androidx.lifecycle.ViewModel
import com.vkondrav.playground.app.base.item.ComposableAction
import com.vkondrav.playground.app.base.item.ComposableItem

abstract class BaseViewModel : ViewModel() {
    abstract val columnItems: List<ComposableItem>
    abstract fun fetchData()
    abstract fun onAction(action: ComposableAction)
}