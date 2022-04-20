package com.vkondrav.playground.app.drawer.composable

import androidx.compose.material.DrawerState
import androidx.compose.material.ModalDrawer
import androidx.compose.runtime.Composable
import com.vkondrav.playground.app.base.composable.screen.BaseScreen
import com.vkondrav.playground.app.common.action.FetchDataAction
import com.vkondrav.playground.app.drawer.viewmodel.DrawerViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun Drawer(drawerState: DrawerState, content: @Composable () -> Unit) {

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