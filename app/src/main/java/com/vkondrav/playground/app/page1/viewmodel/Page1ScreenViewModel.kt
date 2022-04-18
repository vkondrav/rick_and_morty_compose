package com.vkondrav.playground.app.page1.viewmodel

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.mutableStateListOf
import androidx.navigation.NavController
import com.vkondrav.playground.app.base.item.ComposableAction
import com.vkondrav.playground.app.base.item.ComposableItem
import com.vkondrav.playground.app.base.viewmodel.BaseViewModel
import com.vkondrav.playground.app.common.action.MessageCardAction
import com.vkondrav.playground.app.common.composable.MessageCardItem
import com.vkondrav.playground.app.common.navigation.Route

class Page1ScreenViewModel(private val navController: NavController) : BaseViewModel() {

    private val _columnItems = mutableStateListOf<ComposableItem>()
    override val columnItems: List<ComposableItem> = _columnItems

    override fun fetchData() {
        _columnItems.clear()
        (1..1_000).forEach { i ->
            mutableListOf<ComposableItem>().apply {
                _columnItems.add(
                    MessageCardItem(
                        message = "Item $i",
                        image = Icons.Default.AccountCircle
                    )
                )
            }
        }
    }

    override fun onAction(action: ComposableAction) {
        when (action) {
            is MessageCardAction -> navController.navigate(Route.Screen2)
        }
    }
}