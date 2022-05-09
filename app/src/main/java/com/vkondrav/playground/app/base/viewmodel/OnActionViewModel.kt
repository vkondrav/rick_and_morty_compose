package com.vkondrav.playground.app.base.viewmodel

import com.vkondrav.playground.app.base.item.ComposableAction

interface OnActionViewModel {
    fun onAction(action: ComposableAction)
}