package com.vkondrav.ram.navigation.usecase

import androidx.navigation.NavController
import com.vkondrav.ram.navigation.Navigator
import com.vkondrav.ram.navigation.data.AppBarState
import kotlinx.coroutines.flow.Flow

interface FetchAppBarStateUseCase {
    operator fun invoke(navController: NavController): Flow<AppBarState>
}

internal fun fetchAppBarStateUseCase(
    navigator: Navigator,
) = object : FetchAppBarStateUseCase {
    override operator fun invoke(navController: NavController) =
        navigator.observeBackStack(navController)
}
