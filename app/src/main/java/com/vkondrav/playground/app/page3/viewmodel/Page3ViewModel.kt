package com.vkondrav.playground.app.page3.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.navigation.NavController
import com.vkondrav.playground.app.base.item.ComposableAction
import com.vkondrav.playground.app.base.item.ComposableItem
import com.vkondrav.playground.app.base.viewmodel.BaseViewModel
import com.vkondrav.playground.app.common.action.ViewBindingAction
import com.vkondrav.playground.app.common.composable.ViewBindingCardItem
import com.vkondrav.playground.app.common.navigation.Route

class Page3ScreenViewModel(private val navController: NavController) :
    BaseViewModel() {

    private val _columnItems = mutableStateListOf<ComposableItem>()
    override val columnItems: List<ComposableItem> = _columnItems

    override fun fetchData() {
        _columnItems.clear()
        (1..1_000).forEach { i ->
            mutableListOf<ComposableItem>().apply {
                _columnItems.add(
                    ViewBindingCardItem(
                        text = "XML Text $i"
                    )
                )
            }
        }
    }

    override fun onAction(action: ComposableAction) {
        when (action) {
            is ViewBindingAction -> navController.navigate(Route.Screen1)
        }
    }
}