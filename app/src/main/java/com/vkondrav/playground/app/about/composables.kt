package com.vkondrav.playground.app.about

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun AboutScreen() {
}

// -- Previews -----------------------------------------------------------------

private fun init() {
}

@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
    init()
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ScreenDarkPreview() {
    init()
}
