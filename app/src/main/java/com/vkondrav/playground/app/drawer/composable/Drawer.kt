package com.vkondrav.playground.app.drawer.composable

import androidx.compose.material.DrawerValue
import androidx.compose.material.ModalDrawer
import androidx.compose.material.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import com.vkondrav.playground.app.base.composable.screen.BaseScreen
import com.vkondrav.playground.app.common.action.FetchDataAction
import com.vkondrav.playground.app.common.di.loadIntoKoin
import com.vkondrav.playground.app.drawer.viewmodel.DrawerViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun Drawer(content: @Composable () -> Unit) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    drawerState.loadIntoKoin()

    val scope = rememberCoroutineScope()
    scope.loadIntoKoin()

    val viewModel = getViewModel<DrawerViewModel>().also {
        it.onAction(FetchDataAction)
    }

    ModalDrawer(
        drawerState = drawerState,
        gesturesEnabled = drawerState.isOpen,
        drawerContent = {
            BaseScreen(viewModel)
        },
        content = content,
    )
}