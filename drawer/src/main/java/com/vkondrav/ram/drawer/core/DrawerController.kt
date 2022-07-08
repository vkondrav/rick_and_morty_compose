package com.vkondrav.ram.drawer.core

import androidx.compose.material.DrawerState
import com.vkondrav.ram.common.util.FlowWrapper
import com.vkondrav.ram.drawer.data.DrawerCommand
import kotlinx.coroutines.flow.MutableSharedFlow

internal class DrawerController(private val wrapper: FlowWrapper = FlowWrapper()) {

    private val drawerCommands =
        MutableSharedFlow<DrawerCommand>(extraBufferCapacity = Int.MAX_VALUE)

    fun open() {
        drawerCommands.tryEmit(DrawerCommand.Open)
    }

    fun close() {
        drawerCommands.tryEmit(DrawerCommand.Close)
    }

    suspend fun handleDrawerState(drawerState: DrawerState) {
        wrapper(drawerCommands).collect {
            drawerState.handle(it)
        }
    }

    private suspend fun DrawerState.handle(drawerCommand: DrawerCommand) {
        when (drawerCommand) {
            is DrawerCommand.Open -> open()
            is DrawerCommand.Close -> close()
        }
    }
}
