package com.vkondrav.playground.app.drawer.viewmodel

import androidx.compose.material.DrawerState
import androidx.compose.runtime.mutableStateListOf
import androidx.navigation.NavController
import com.vkondrav.playground.app.base.item.ComposableAction
import com.vkondrav.playground.app.base.item.ComposableItem
import com.vkondrav.playground.app.base.item.OnAction
import com.vkondrav.playground.app.base.viewmodel.BaseViewModel
import com.vkondrav.playground.app.common.action.NavigateAction
import com.vkondrav.playground.app.common.action.NavigateBackAction
import com.vkondrav.playground.app.common.action.OpenDrawerAction
import com.vkondrav.playground.app.common.composable.MenuItemItem
import com.vkondrav.playground.app.common.navigation.Route
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class DrawerViewModel(
    val navController: NavController,
    private val drawerState: DrawerState,
    private val scope: CoroutineScope,
) : BaseViewModel(),
    OnAction {

    private val _columnItems = mutableStateListOf<ComposableItem>()
    override val columnItems: List<ComposableItem> = _columnItems

    override fun fetchData() {
        _columnItems.clear()
        Route.allScreens.forEach { screen ->
            _columnItems.add(MenuItemItem(screen))
        }
    }

    override fun onAction(action: ComposableAction) {
        when (action) {
            is NavigateAction -> {
                navController.navigate(action.route)
                scope.launch { drawerState.close() }
            }
            is OpenDrawerAction -> {
                scope.launch { drawerState.open() }
            }
            is NavigateBackAction -> navController.popBackStack()
        }
    }
}