package com.vkondrav.playground.app.page1.viewmodel

import androidx.compose.material.SnackbarHostState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import com.vkondrav.playground.app.base.item.ComposableAction
import com.vkondrav.playground.app.base.item.ComposableItem
import com.vkondrav.playground.app.base.viewmodel.BaseViewModel
import com.vkondrav.playground.app.base.viewmodel.OnActionViewModel
import com.vkondrav.playground.app.base.viewmodel.ScreenEventViewModel
import com.vkondrav.playground.app.common.action.FetchDataAction
import com.vkondrav.playground.app.common.action.MessageCardAction
import com.vkondrav.playground.app.common.composable.CollapsableViewItem
import com.vkondrav.playground.app.common.composable.MessageViewItem
import com.vkondrav.playground.app.common.composable.PageLoadingViewItem
import com.vkondrav.playground.app.common.event.ScreenEvent
import com.vkondrav.playground.app.common.scope.ComposableScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

class Page1ScreenViewModel(
    private val snackbarHostState: SnackbarHostState,
    override val composableScope: CoroutineScope,
    dispatcher: CoroutineDispatcher,
) : BaseViewModel(dispatcher), ScreenEventViewModel, ComposableScope,
    OnActionViewModel {

    private val _screenEvent = MutableStateFlow<ScreenEvent?>(null)
    override val screenEvent: Flow<ScreenEvent> = _screenEvent.filterNotNull()

    private fun fetchData() {
        launch {
            _screenEvent.value = ScreenEvent.Loading(PageLoadingViewItem)
            delay(5_000)
            val items = mutableListOf<ComposableItem>()

            (1..1_000).forEach { i ->
                mutableListOf<ComposableItem>().apply {
                    items.add(
                        CollapsableViewItem(
                            title = "Drawer $i",
                            open = i % 2 == 0,
                            items = mutableListOf<ComposableItem>().apply {
                                (1..5).forEach { j ->
                                    add(
                                        MessageViewItem(
                                            message = "Title $j",
                                            image = Icons.Default.AccountCircle,
                                        )
                                    )
                                }
                            },
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
            is MessageCardAction -> launchComposable {
                snackbarHostState.showSnackbar("Message: ${action.message}")
            }
        }
    }
}