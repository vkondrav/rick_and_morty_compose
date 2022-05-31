package com.vkondrav.playground.app.base.composable.screen

import androidx.compose.runtime.Composable
import com.vkondrav.playground.app.base.viewmodel.ScreenEventViewModel
import com.vkondrav.playground.app.base.viewmodel.collect
import com.vkondrav.playground.app.common.event.ScreenEvent

@Composable
fun BaseScreen(
    screenEventViewModel: ScreenEventViewModel,
) {
    when(val screenEvent = screenEventViewModel.collect()) {
        is ScreenEvent.Loading,
        is ScreenEvent.Content,
        is ScreenEvent.Error, -> screenEvent.item.Composable()
        null -> {
            //no-op
        }
    }
}
