package com.vkondrav.ram.drawer.data

sealed class DrawerCommand {
    object Open : DrawerCommand()
    object Close : DrawerCommand()
}
