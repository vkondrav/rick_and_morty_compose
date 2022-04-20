package com.vkondrav.playground.app.page1.composable

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.vkondrav.playground.app.base.composable.screen.BaseScreen
import com.vkondrav.playground.app.common.action.FetchDataAction
import com.vkondrav.playground.app.page1.viewmodel.Page1ScreenViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun Page1Screen() {
    val viewModel = getViewModel<Page1ScreenViewModel>().also {
        it.onAction(FetchDataAction)
    }
    BaseScreen(viewModel)
}

// -- Previews -----------------------------------------------------------------
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ScreenDarkPreview() {
    Page1Screen()
}