package com.vkondrav.playground.app.base.viewmodel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.vkondrav.playground.app.common.event.ScreenEvent
import kotlinx.coroutines.flow.Flow

interface ScreenEventViewModel {
    val screenEvent: Flow<ScreenEvent>
}

@Composable
fun ScreenEventViewModel.collect() = screenEvent.collectAsState()

@Composable
private fun Flow<ScreenEvent>.collectAsState() =
    collectAsState(initial = ScreenEvent.None)
        .value