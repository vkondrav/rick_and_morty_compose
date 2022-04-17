package com.vkondrav.playground.app.common.composable.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.vkondrav.playground.app.common.viewmodel.BaseViewModel

@Composable
fun BaseScreen(viewModel: BaseViewModel) {
    LazyColumn {
        items(viewModel.columnItems) { item ->
            item.Composable(viewModel::onAction)
        }
    }
}