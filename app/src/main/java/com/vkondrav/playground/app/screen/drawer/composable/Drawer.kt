package com.vkondrav.playground.app.screen.drawer.composable

import androidx.compose.material.DrawerState
import androidx.compose.material.ModalDrawer
import androidx.compose.runtime.Composable
import com.vkondrav.playground.app.base.composable.screen.BaseScreen
import com.vkondrav.playground.app.screen.drawer.viewmodel.DrawerViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun CustomDrawer(drawerState: DrawerState, content: @Composable () -> Unit) {

    val viewModel = getViewModel<DrawerViewModel>().also {
        it.fetchMenu()
    }

    ModalDrawer(
        drawerState = drawerState,
        gesturesEnabled = drawerState.isOpen,
        drawerContent = {
            BaseScreen(
                screenEventViewModel = viewModel,
            )
        },
        content = content,
    )
}