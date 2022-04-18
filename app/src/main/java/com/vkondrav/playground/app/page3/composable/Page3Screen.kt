package com.vkondrav.playground.app.page3.composable

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.vkondrav.playground.app.base.composable.screen.BaseScreen
import com.vkondrav.playground.app.page3.viewmodel.Page3ScreenViewModel

@Composable
fun Page3Screen(navController: NavController) {
    val viewModel = Page3ScreenViewModel(navController).apply { fetchData() }
    BaseScreen(viewModel)
}

// -- Previews -----------------------------------------------------------------
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ScreenDarkPreview() {
    Page3Screen(rememberNavController())
}