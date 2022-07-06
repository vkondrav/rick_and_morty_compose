package com.vkondrav.ram.app.screen.drawer.composable

import androidx.compose.material.DrawerValue
import androidx.compose.material.ModalDrawer
import androidx.compose.material.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.vkondrav.ram.common.ui.BaseStateScreen
import com.vkondrav.ram.app.screen.drawer.viewmodel.DrawerViewModel
import com.vkondrav.ram.app.screen.main.usecase.HandleDrawerStateUseCase
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
