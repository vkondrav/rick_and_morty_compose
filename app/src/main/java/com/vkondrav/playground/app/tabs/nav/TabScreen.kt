package com.vkondrav.playground.app.tabs.nav

import android.os.Bundle
import androidx.compose.runtime.Composable
import com.vkondrav.playground.app.common.utils.TextResource

data class TabScreen(
    val title: TextResource,
    val compose: @Composable (Bundle?) -> Unit,
)
