package com.vkondrav.playground.app.page3.viewmodel

import android.util.Log
import com.vkondrav.playground.app.base.item.ComposableAction
import com.vkondrav.playground.app.base.item.ComposableItem
import com.vkondrav.playground.app.base.viewmodel.BaseViewModel
import com.vkondrav.playground.app.base.viewmodel.OnActionViewModel
import com.vkondrav.playground.app.base.viewmodel.ScreenEventViewModel
import com.vkondrav.playground.app.common.action.FetchDataAction
import com.vkondrav.playground.app.common.action.ViewBindingAction
import com.vkondrav.playground.app.common.composable.SampleViewBindingViewItem
import com.vkondrav.playground.app.common.event.ScreenEvent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

class Page3ScreenViewModel(
    dispatcher: CoroutineDispatcher,
) : BaseViewModel(dispatcher), ScreenEventViewModel, OnActionViewModel {

    private val _screenEvent = MutableStateFlow<ScreenEvent?>(null)
    override val screenEvent: Flow<ScreenEvent> = _screenEvent.filterNotNull()

    private fun fetchData() {
        launch {
            val items = mutableListOf<ComposableItem>()
            (1..1_000).forEach { i ->
                mutableListOf<ComposableItem>().apply {
                    items.add(
                        SampleViewBindingViewItem(
                            text = "XML Text $i"
                        )
                    )
                }
            }

            _screenEvent.value = ScreenEvent.Column(items)
        }
    }

    override fun onAction(action: ComposableAction) {
        when (action) {
            is FetchDataAction -> fetchData()
            is ViewBindingAction -> Log.i("Action", action.toString())
        }
    }
}