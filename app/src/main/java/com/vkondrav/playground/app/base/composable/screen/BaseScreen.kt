package com.vkondrav.playground.app.base.composable.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.vkondrav.playground.app.base.viewmodel.OnActionViewModel
import com.vkondrav.playground.app.base.viewmodel.ScreenEventViewModel
import com.vkondrav.playground.app.base.viewmodel.collectEvent
import com.vkondrav.playground.app.common.event.ScreenEvent

@Composable
fun BaseScreen(
    screenEventViewModel: ScreenEventViewModel,
    onActionViewModel: OnActionViewModel,
) {
    when(val screenEvent = screenEventViewModel.collectEvent()) {
        is ScreenEvent.Loading -> screenEvent.item.Composable(onActionViewModel::onAction)
        is ScreenEvent.Column -> {
            LazyColumn {
                items(screenEvent.items) { item ->
                    item.Composable(onActionViewModel::onAction)
                }
            }
        }
        is ScreenEvent.Error -> screenEvent.item.Composable(onActionViewModel::onAction)
    }
}