package com.vkondrav.ram.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.vkondrav.ram.common.ui.data.TextResource
import kotlinx.coroutines.flow.Flow

data class AppBarState(
    val showBackButton: Boolean,
    val title: TextResource,
)

@Composable
fun Flow<AppBarState>.collectAsState() = collectAsState(
    initial = AppBarState(
        showBackButton = false,
        title = TextResource.Literal(""),
    ),
)
