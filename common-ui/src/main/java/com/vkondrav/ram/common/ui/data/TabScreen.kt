package com.vkondrav.ram.common.ui.data

import android.os.Bundle
import androidx.compose.runtime.Composable

data class TabScreen(
    val title: TextResource,
    val compose: @Composable (Bundle?) -> Unit,
)
