package com.vkondrav.ram.app.screen.drawer.composable

import androidx.compose.material.DrawerValue
import androidx.compose.material.ModalDrawer
import androidx.compose.material.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.vkondrav.ram.app.base.composable.screen.BaseStateScreen
import com.vkondrav.ram.app.common.drawer.DrawerController
import com.vkondrav.ram.app.screen.drawer.viewmodel.DrawerViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun CustomDrawer(drawerController: DrawerController, content: @Composable () -> Unit) {
    val drawerState = rememberDrawerState(
        initialValue = DrawerValue.Closed,
    )

    LaunchedEffect(drawerController) {
        drawerController.handleDrawerState(drawerState)
    }

    ModalDrawer(
        drawerState = drawerState,
        gesturesEnabled = drawerState.isOpen,
        drawerContent = {
            BaseStateScreen(
                viewModel = getViewModel<DrawerViewModel>(),
            )
        },
        content = content,
    )
}
