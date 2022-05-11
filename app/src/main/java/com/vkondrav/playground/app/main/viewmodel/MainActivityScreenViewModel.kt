package com.vkondrav.playground.app.main.viewmodel

import com.vkondrav.playground.app.base.item.ComposableAction
import com.vkondrav.playground.app.base.viewmodel.BaseViewModel
import com.vkondrav.playground.app.base.viewmodel.OnActionViewModel
import com.vkondrav.playground.app.common.state.AppState
import kotlinx.coroutines.CoroutineDispatcher

class MainActivityScreenViewModel(
    private val appState: AppState,
    dispatcher: CoroutineDispatcher,
): BaseViewModel(dispatcher), OnActionViewModel {

    override fun onAction(action: ComposableAction) {
        when(action) {
            is OpenBottomSheet -> openBottomSheet()
        }
    }

    private fun openBottomSheet() {
        appState.openBottomSheet()
    }
}

object OpenBottomSheet: ComposableAction