package com.vkondrav.playground.app.home

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

var start: Long = System.currentTimeMillis()

@Composable
fun HomeScreen() {
}

// -- Previews -----------------------------------------------------------------

@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ScreenDarkPreview() {
}
