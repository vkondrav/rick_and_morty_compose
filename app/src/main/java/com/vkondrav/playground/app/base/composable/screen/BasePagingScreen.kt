package com.vkondrav.playground.app.base.composable.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.vkondrav.playground.app.base.viewmodel.PagingViewModel
import com.vkondrav.playground.app.common.composable.PageLoadingView

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
            when (loadState.append) {
                is LoadState.Loading -> {
                    item { PagingItemLoadingView() }
                }
                is LoadState.Error -> {
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
