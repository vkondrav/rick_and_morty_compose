package com.vkondrav.playground.app.page3.composable

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.vkondrav.playground.app.base.composable.screen.BaseScreen
import com.vkondrav.playground.app.page3.viewmodel.Page3ScreenViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun Page3Screen() {
    val viewModel = getViewModel<Page3ScreenViewModel>()
    viewModel.fetchData()
    BaseScreen(viewModel)
}

// -- Previews -----------------------------------------------------------------
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ScreenDarkPreview() {
    Page3Screen()
}