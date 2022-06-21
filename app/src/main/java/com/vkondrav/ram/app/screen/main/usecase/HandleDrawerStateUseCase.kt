package com.vkondrav.ram.app.screen.main.usecase

import androidx.compose.material.DrawerState
import com.vkondrav.ram.app.common.drawer.DrawerController

class HandleDrawerStateUseCase(
    private val drawerController: DrawerController,
) {
    suspend operator fun invoke(drawerState: DrawerState) =
        drawerController.handleDrawerState(drawerState)
}
