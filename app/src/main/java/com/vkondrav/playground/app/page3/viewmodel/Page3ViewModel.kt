package com.vkondrav.playground.app.page3.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.navigation.NavController
import com.vkondrav.playground.app.base.item.ComposableAction
import com.vkondrav.playground.app.base.item.ComposableItem
import com.vkondrav.playground.app.base.viewmodel.BaseViewModel
import com.vkondrav.playground.app.common.action.FetchDataAction
import com.vkondrav.playground.app.common.action.ViewBindingAction
import com.vkondrav.playground.app.common.composable.ViewBindingCardItem
import com.vkondrav.playground.app.common.navigation.Route
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class Page3ScreenViewModel(
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
                        ViewBindingCardItem(
                            text = "XML Text $i"
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
            is ViewBindingAction -> navController.navigate(Route.Screen1.id)
        }
    }
}