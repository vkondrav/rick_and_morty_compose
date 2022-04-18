package com.vkondrav.playground.app.page2.composable

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.vkondrav.playground.app.base.composable.screen.BaseScreen
import com.vkondrav.playground.app.page2.viewmodel.Page2ScreenViewModel

@Composable
fun Page2Screen(navController: NavController) {
    val viewModel = Page2ScreenViewModel(navController).apply { fetchData() }
    BaseScreen(viewModel)
}

// -- Previews -----------------------------------------------------------------
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ScreenDarkPreview() {
    Page2Screen(rememberNavController())
}