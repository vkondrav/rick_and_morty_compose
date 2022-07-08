package com.vkondrav.ram.drawer.usecase

import com.vkondrav.ram.drawer.core.DrawerController

interface OpenDrawerUseCase {
    operator fun invoke()
}

internal fun openDrawerUseCase(
    drawerController: DrawerController,
) = object : OpenDrawerUseCase {
    override operator fun invoke() {
        drawerController.open()
    }
}
