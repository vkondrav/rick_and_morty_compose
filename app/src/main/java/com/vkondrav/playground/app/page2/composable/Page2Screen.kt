package com.vkondrav.playground.app.page2.composable

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.vkondrav.playground.app.base.composable.screen.BaseScreen
import com.vkondrav.playground.app.page2.viewmodel.Page2ScreenViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun Page2Screen() {
    val viewModel = getViewModel<Page2ScreenViewModel>()
    viewModel.fetchData()
    BaseScreen(viewModel)
}

// -- Previews -----------------------------------------------------------------
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ScreenDarkPreview() {
    Page2Screen()
}