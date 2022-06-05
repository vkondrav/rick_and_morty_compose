package com.vkondrav.playground.app.base.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel(private val dispatcher: CoroutineDispatcher) :
    ViewModel(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = dispatcher + exceptionHandler

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Timber.e(throwable)
    }

    override fun onCleared() {
        super.onCleared()
        coroutineContext.cancel()
    }
}
