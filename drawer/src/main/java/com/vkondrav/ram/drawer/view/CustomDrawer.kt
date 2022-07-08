package com.vkondrav.ram.drawer.view

import androidx.compose.material.DrawerValue
import androidx.compose.material.ModalDrawer
import androidx.compose.material.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.vkondrav.ram.common.ui.screen.BaseStateScreen
import com.vkondrav.ram.drawer.usecase.HandleDrawerStateUseCase
import com.vkondrav.ram.drawer.viewmodel.DrawerViewModel
import org.koin.androidx.compose.get
import org.koin.androidx.compose.getViewModel

@Composable
fun CustomDrawer(
    handleDrawerStateUseCase: HandleDrawerStateUseCase = get(),
    content: @Composable () -> Unit,
) {
    val drawerState = rememberDrawerState(
        initialValue = DrawerValue.Closed,
    )

    LaunchedEffect("drawer") {
        handleDrawerStateUseCase(drawerState)
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
