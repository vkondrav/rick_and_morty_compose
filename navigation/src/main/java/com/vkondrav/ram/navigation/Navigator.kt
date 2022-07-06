package com.vkondrav.ram.navigation

import androidx.navigation.NavController
import com.vkondrav.ram.common.util.FlowWrapper
import com.vkondrav.ram.navigation.data.AppBarState
import com.vkondrav.ram.navigation.data.NavigationCommand
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.map

internal class Navigator(private val flowWrapper: FlowWrapper) {

    private val navigationCommands =
        MutableSharedFlow<NavigationCommand>(extraBufferCapacity = Int.MAX_VALUE)

    fun navigate(route: String) {
        navigationCommands.tryEmit(NavigationCommand.NavigateToRoute(route))
    }

    fun navigateUp() {
        navigationCommands.tryEmit(NavigationCommand.NavigateUp)
    }

    suspend fun handleNavigationCommands(navController: NavController) {
        flowWrapper(navigationCommands).collect {
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
