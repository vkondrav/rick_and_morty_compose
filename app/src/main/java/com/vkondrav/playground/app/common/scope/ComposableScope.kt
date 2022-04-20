package com.vkondrav.playground.app.common.scope

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

interface ComposableScope {
    val composableScope: CoroutineScope
    fun launchComposable(block: suspend CoroutineScope.() -> Unit) =
        composableScope.launch(block = block)
}