package com.vkondrav.playground.app.home.composable

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.vkondrav.playground.app.common.composable.screen.BaseScreen
import com.vkondrav.playground.app.home.viewmodel.HomeScreenViewModel

@Composable
fun HomeScreen() {
    val viewModel = HomeScreenViewModel().apply { fetchData() }
    BaseScreen(viewModel)
}

// -- Previews -----------------------------------------------------------------
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ScreenDarkPreview() {
    HomeScreen()
}