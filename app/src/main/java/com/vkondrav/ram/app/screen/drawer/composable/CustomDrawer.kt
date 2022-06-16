package com.vkondrav.ram.app.screen.drawer.composable

import androidx.compose.material.DrawerState
import androidx.compose.material.ModalDrawer
import androidx.compose.runtime.Composable
import com.vkondrav.ram.app.base.composable.screen.BaseStateScreen
import com.vkondrav.ram.app.screen.drawer.viewmodel.DrawerViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun CustomDrawer(drawerState: DrawerState, content: @Composable () -> Unit) {
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