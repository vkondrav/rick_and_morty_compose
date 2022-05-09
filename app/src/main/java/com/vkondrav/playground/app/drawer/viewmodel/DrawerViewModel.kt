package com.vkondrav.playground.app.drawer.viewmodel

import androidx.compose.material.DrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import com.vkondrav.playground.app.base.item.ComposableAction
import com.vkondrav.playground.app.base.item.ComposableItem
import com.vkondrav.playground.app.base.viewmodel.BaseViewModel
import com.vkondrav.playground.app.base.viewmodel.OnActionViewModel
import com.vkondrav.playground.app.base.viewmodel.ScreenEventViewModel
import com.vkondrav.playground.app.common.action.FetchDataAction
import com.vkondrav.playground.app.common.action.NavigateAction
import com.vkondrav.playground.app.common.action.NavigateBackAction
import com.vkondrav.playground.app.common.action.OpenDrawerAction
import com.vkondrav.playground.app.common.composable.MenuViewItem
import com.vkondrav.playground.app.common.event.DrawerEvent
import com.vkondrav.playground.app.common.event.ScreenEvent
import com.vkondrav.playground.app.common.navigation.allScreens
import com.vkondrav.playground.app.common.scope.ComposableScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

class DrawerViewModel(
    private val navController: NavController,
    private val drawerState: DrawerState,
    override val composableScope: CoroutineScope,
    dispatcher: CoroutineDispatcher,
) : BaseViewModel(dispatcher), ComposableScope, ScreenEventViewModel, OnActionViewModel {

    private val _drawerEvent = MutableStateFlow<DrawerEvent?>(null)
    val drawerEvent: Flow<DrawerEvent> = _drawerEvent.filterNotNull()

    private val _screenEvent = MutableStateFlow<ScreenEvent?>(null)
    override val screenEvent: Flow<ScreenEvent> = _screenEvent.filterNotNull()

    private fun fetchData() {
        launch {
            val items = mutableListOf<ComposableItem>()

            allScreens.forEach { screen ->
                items.add(MenuViewItem(screen))
            }

            _screenEvent.value = ScreenEvent.Column(items)
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

@Composable
fun DrawerViewModel.collectDrawerEvent() = drawerEvent.collectAsState()

@Composable
private fun Flow<DrawerEvent>.collectAsState() =
    collectAsState(initial = DrawerEvent.None)
        .value