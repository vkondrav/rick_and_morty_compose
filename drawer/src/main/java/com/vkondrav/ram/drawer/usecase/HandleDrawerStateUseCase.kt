package com.vkondrav.ram.drawer.usecase

import androidx.compose.material.DrawerState
import com.vkondrav.ram.drawer.core.DrawerController

interface HandleDrawerStateUseCase {
    suspend operator fun invoke(drawerState: DrawerState)
}

internal fun handleDrawerStateUseCase(
    drawerController: DrawerController,
) = object : HandleDrawerStateUseCase {
    override suspend operator fun invoke(drawerState: DrawerState) =
        drawerController.handleDrawerState(drawerState)
}
