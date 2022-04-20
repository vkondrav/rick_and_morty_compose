package com.vkondrav.playground.app.page1.viewmodel

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.mutableStateListOf
import androidx.navigation.NavController
import com.vkondrav.playground.app.base.item.ComposableAction
import com.vkondrav.playground.app.base.item.ComposableItem
import com.vkondrav.playground.app.base.viewmodel.BaseViewModel
import com.vkondrav.playground.app.common.action.FetchDataAction
import com.vkondrav.playground.app.common.action.MessageCardAction
import com.vkondrav.playground.app.common.composable.CollapsableCardItem
import com.vkondrav.playground.app.common.composable.MessageCardItem
import com.vkondrav.playground.app.common.navigation.Route
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class Page1ScreenViewModel(
    private val navController: NavController,
    dispatcher: CoroutineDispatcher,
) : BaseViewModel(dispatcher) {

    private val _columnItems = mutableStateListOf<ComposableItem>()
    override val columnItems: List<ComposableItem> = _columnItems

    private fun fetchData() {
        launch {
            val tempItems = mutableListOf<ComposableItem>()

            (1..1_000).forEach { i ->
                mutableListOf<ComposableItem>().apply {
                    tempItems.add(
                        CollapsableCardItem(
                            title = "Drawer $i",
                            open = i % 2 == 0,
                            items = mutableListOf<ComposableItem>().apply {
                                (1..5).forEach { j ->
                                    add(
                                        MessageCardItem(
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

            launchMain {
                _columnItems.clear()
                _columnItems.addAll(tempItems)
            }
        }
    }

    override fun onAction(action: ComposableAction) {
        when (action) {
            is FetchDataAction -> fetchData()
            is MessageCardAction -> navController.navigate(Route.Screen2.id)
        }
    }
}