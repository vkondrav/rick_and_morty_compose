package com.vkondrav.ram.app.common.drawer

import androidx.compose.material.DrawerState
import com.vkondrav.ram.domain.util.FlowWrapper
import kotlinx.coroutines.flow.MutableSharedFlow

class DrawerController(private val wrapper: FlowWrapper = FlowWrapper()) {

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
