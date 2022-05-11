package com.vkondrav.playground.app.page3.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.vkondrav.playground.app.base.composable.screen.BaseScreen
import com.vkondrav.playground.app.common.action.FetchDataAction
import com.vkondrav.playground.app.page3.viewmodel.Page3ScreenViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun Page3Screen() {
    val viewModel = getViewModel<Page3ScreenViewModel>().also {
        it.onAction(FetchDataAction)
    }
    BaseScreen(
        screenEventViewModel = viewModel,
        onActionViewModel = viewModel,
    )
}

@Preview
@Composable
private fun ScreenDarkPreview() {
    Page3Screen()
}