package com.vkondrav.playground.app.base.viewmodel

import androidx.lifecycle.ViewModel
import com.vkondrav.playground.app.base.item.ComposableAction
import com.vkondrav.playground.app.base.item.ComposableItem
import com.vkondrav.playground.app.base.item.OnAction

abstract class BaseViewModel : ViewModel(), OnAction {
    abstract val columnItems: List<ComposableItem>
    abstract fun fetchData()
}