package com.vkondrav.playground.app.drawer.viewmodel

import androidx.compose.material.DrawerState
import androidx.compose.runtime.mutableStateListOf
import androidx.navigation.NavController
import com.vkondrav.playground.app.base.item.ComposableAction
import com.vkondrav.playground.app.base.item.ComposableItem
import com.vkondrav.playground.app.base.viewmodel.BaseViewModel
import com.vkondrav.playground.app.common.action.FetchDataAction
import com.vkondrav.playground.app.common.action.NavigateAction
import com.vkondrav.playground.app.common.action.NavigateBackAction
import com.vkondrav.playground.app.common.action.OpenDrawerAction
import com.vkondrav.playground.app.common.composable.MenuViewItem
import com.vkondrav.playground.app.common.navigation.allScreens
import com.vkondrav.playground.app.common.scope.ComposableScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class DrawerViewModel(
    private val navController: NavController,
    private val drawerState: DrawerState,
    override val composableScope: CoroutineScope,
    dispatcher: CoroutineDispatcher,
) : BaseViewModel(dispatcher), ComposableScope {

    private val _columnItems = mutableStateListOf<ComposableItem>()
    override val columnItems: List<ComposableItem> = _columnItems

    private fun fetchData() {
        launch {
            val tempItems = mutableListOf<ComposableItem>()

            allScreens.forEach { screen ->
                tempItems.add(MenuViewItem(screen))
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
            is NavigateAction -> {
                navController.navigate(action.route)
                launchComposable { drawerState.close() }
            }
            is OpenDrawerAction -> {
                launchComposable { drawerState.open() }
            }
            is NavigateBackAction -> navController.popBackStack()
        }
    }
}