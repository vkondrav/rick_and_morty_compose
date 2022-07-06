package com.vkondrav.ram.common.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.vkondrav.ram.common.ui.view.PageLoadingView
import com.vkondrav.ram.common.ui.viewmodel.PagingViewModel

@Composable
fun BasePagingScreen(
    pagingViewModel: PagingViewModel,
) {
    val items = pagingViewModel.pagingData.collectAsLazyPagingItems()

    LazyColumn {
        items(items) { item ->
            item?.Composable()
        }
        items.apply {
            when (val state = loadState.append) {
                is LoadState.Loading -> {
                    item { PagingItemLoadingView() }
                }
                is LoadState.Error -> {
                    item { PagingItemErrorView(exception = state.error) }
                }
                else -> {
                    //no-op
                }
            }
        }
    }
    items.apply {
        when (loadState.refresh) {
            is LoadState.Loading -> {
                PageLoadingView()
            }
            else -> {
                //no-op
            }
        }
    }
}

@Composable
fun PagingItemLoadingView() {
    Box(
        Modifier
            .wrapContentSize()
            .fillMaxWidth(),
    ) {
        CircularProgressIndicator(
            Modifier.align(Alignment.Center),
        )
    }
}

@Composable
fun PagingItemErrorView(exception: Throwable) {
    Box(
        Modifier
            .wrapContentSize()
            .fillMaxWidth(),
    ) {
        Text(
            text = exception.message ?: "unknown error has occurred",
            modifier = Modifier.align(Alignment.Center),
        )
    }
}
