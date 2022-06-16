package com.vkondrav.ram.app.base.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel(private val dispatcher: CoroutineDispatcher) :
    ViewModel(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = dispatcher + exceptionHandler

    override fun onCleared() {
        super.onCleared()
        coroutineContext.cancel()
    }

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Timber.e(throwable)
    }

    protected fun <T> Flow<T>.mapState(
        initialValue: T,
        onEach: ((T) -> Unit)? = null,
    ): StateFlow<T> = mapLatest {
        onEach?.invoke(it)
        it
    }.stateIn(
        scope = this@BaseViewModel,
        started = SharingStarted.WhileSubscribed(),
        initialValue = initialValue,
    )
}
