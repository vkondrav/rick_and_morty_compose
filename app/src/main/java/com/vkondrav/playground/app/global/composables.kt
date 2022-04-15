package com.vkondrav.playground.app.global

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HostScreen(content: @Composable (padding: PaddingValues) -> Unit = {}) {
}

// -- Previews -----------------------------------------------------------------

private fun init() {
}

@ExperimentalAnimationApi
@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
    init()
}

@ExperimentalAnimationApi
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ScreenDarkPreview() {
    init()
}
