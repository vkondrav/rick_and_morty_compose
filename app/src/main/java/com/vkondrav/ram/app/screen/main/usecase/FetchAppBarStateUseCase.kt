package com.vkondrav.ram.app.screen.main.usecase

import androidx.navigation.NavController
import com.vkondrav.ram.app.common.navigation.Navigator

class FetchAppBarStateUseCase(
    private val navigator: Navigator,
) {
    operator fun invoke(navController: NavController) = navigator.observeBackStack(navController)
}
