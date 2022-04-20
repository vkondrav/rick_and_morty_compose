package com.vkondrav.playground.app.base.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.vkondrav.playground.app.base.item.ComposableAction
import com.vkondrav.playground.app.base.item.ComposableItem
import com.vkondrav.playground.app.base.item.OnAction
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel(private val dispatcher: CoroutineDispatcher) :
    ViewModel(), OnAction, CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = dispatcher + exceptionHandler

    abstract val columnItems: List<ComposableItem>

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e("BaseViewModel", throwable.message, throwable)
    }

    protected fun launchMain(block: suspend CoroutineScope.() -> Unit) =
        launch(Dispatchers.Main + exceptionHandler, block = block)
}