package com.vkondrav.playground.app.base.composable.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.vkondrav.playground.app.base.viewmodel.ScreenEventViewModel
import com.vkondrav.playground.app.base.viewmodel.collect
import com.vkondrav.playground.app.common.event.ScreenEvent

@Composable
fun BaseScreen(
    screenEventViewModel: ScreenEventViewModel,
) {
    when(val screenEvent = screenEventViewModel.collect()) {
        is ScreenEvent.Loading -> screenEvent.item.Composable()
        is ScreenEvent.Column -> {
            LazyColumn {
                items(screenEvent.items) { item ->
                    item.Composable()
                }
            }
        }
        is ScreenEvent.Error -> screenEvent.item.Composable()
        else -> {
            //no-op
        }
    }
}