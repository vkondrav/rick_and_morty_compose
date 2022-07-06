package com.vkondrav.ram.navigation.data

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument

data class Screen(
    val route: String,
    val arguments: List<NamedNavArgument> = emptyList(),
    val compose: @Composable (Bundle?) -> Unit,
)
