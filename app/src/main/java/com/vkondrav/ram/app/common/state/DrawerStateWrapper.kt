package com.vkondrav.ram.app.common.state

import androidx.compose.material.DrawerState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class DrawerStateWrapper(
    private val drawerState: DrawerState,
    private val scope: CoroutineScope,
) {

    fun close() {
        scope.launch {
            drawerState.close()
        }
    }
}
