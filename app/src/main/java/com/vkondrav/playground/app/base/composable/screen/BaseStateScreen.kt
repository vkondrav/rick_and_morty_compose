package com.vkondrav.playground.app.base.composable.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.vkondrav.playground.app.base.viewmodel.ScreenState
import com.vkondrav.playground.app.base.viewmodel.ScreenStateViewModel

@Composable
fun BaseStateScreen(
    viewModel: ScreenStateViewModel,
) {
    val items = viewModel.items.collectAsState(initial = emptyList()).value

    LazyColumn {
        items(items) { item ->
            item.Composable()
        }
    }
    viewModel.screenState.apply {
        when (val state = value) {
            is ScreenState.Error -> {
                state.item.Composable()
            }
            is ScreenState.Loading -> {
                state.item.Composable()
            }
            else -> {
                //no-op
            }
        }
    }
}
