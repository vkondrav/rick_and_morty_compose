package com.vkondrav.ram.common.ui.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.vkondrav.ram.common.ui.data.ScreenState
import com.vkondrav.ram.common.ui.viewmodel.ScreenStateViewModel

@Composable
fun BaseStateScreen(
    viewModel: ScreenStateViewModel,
) {
    val items by viewModel.items.collectAsState()
    val screenState by viewModel.screenState

    when (val state = screenState) {
        is ScreenState.Error -> {
            state.item.Composable()
        }
        is ScreenState.Loading -> {
            state.item.Composable()
        }
        is ScreenState.Content -> {
            LazyColumn {
                items(items) { item ->
                    item.Composable()
                }
            }
        }
    }
}
