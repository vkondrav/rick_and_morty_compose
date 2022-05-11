package com.vkondrav.playground.app.characters.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.vkondrav.playground.app.base.composable.screen.BaseScreen
import com.vkondrav.playground.app.characters.viewmodel.CharactersViewModel
import com.vkondrav.playground.app.common.action.FetchDataAction
import org.koin.androidx.compose.getViewModel

@Composable
fun CharactersScreen() {
    val viewModel = getViewModel<CharactersViewModel>().also {
        it.onAction(FetchDataAction)
    }

    BaseScreen(
        screenEventViewModel = viewModel,
        onActionViewModel = viewModel
    )
}

@Composable
fun LoadingItem() {
    CircularProgressIndicator(
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
            .padding(16.dp)
            .wrapContentWidth(
                Alignment.CenterHorizontally
            )
    )
}