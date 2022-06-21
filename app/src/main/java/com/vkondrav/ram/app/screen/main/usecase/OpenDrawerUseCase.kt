package com.vkondrav.ram.app.screen.main.usecase

import com.vkondrav.ram.app.common.drawer.DrawerController

class OpenDrawerUseCase(
    private val drawerController: DrawerController,
) {
    operator fun invoke() {
        drawerController.open()
    }
}
