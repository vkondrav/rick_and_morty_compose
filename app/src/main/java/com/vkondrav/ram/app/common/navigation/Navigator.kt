package com.vkondrav.ram.app.common.navigation

import androidx.navigation.NavController
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.map

class Navigator {

    private val navigationCommands =
        MutableSharedFlow<NavigationCommand>(extraBufferCapacity = Int.MAX_VALUE)

    fun navigate(route: String) {
        navigationCommands.tryEmit(NavigationCommand.NavigateToRoute(route))
    }

    fun navigateUp() {
        navigationCommands.tryEmit(NavigationCommand.NavigateUp)
    }

    suspend fun handleNavigationCommands(navController: NavController) {
        navigationCommands.collect {
            navController.handle(it)
        }
    }

    fun observeBackStack(navController: NavController) =
        navController.currentBackStackEntryFlow.map { state ->
            AppBarState(
                showBackButton = navController.backQueue.size > 2,
                title = state.arguments.title,
            )
        }

    private fun NavController.handle(navigationCommand: NavigationCommand) {
        when (navigationCommand) {
            is NavigationCommand.NavigateToRoute -> {
                navigate(navigationCommand.route)
            }
            NavigationCommand.NavigateUp -> navigateUp()
        }
    }
}
