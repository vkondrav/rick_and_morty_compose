package com.vkondrav.ram.navigation.usecase

import androidx.navigation.NavController
import com.vkondrav.ram.navigation.Navigator

class FetchAppBarStateUseCase(
    private val navigator: Navigator,
) {
    operator fun invoke(navController: NavController) = navigator.observeBackStack(navController)
}
