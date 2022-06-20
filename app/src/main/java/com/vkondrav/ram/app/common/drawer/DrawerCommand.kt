package com.vkondrav.ram.app.common.drawer

sealed class DrawerCommand {
    object Open : DrawerCommand()
    object Close : DrawerCommand()
}
