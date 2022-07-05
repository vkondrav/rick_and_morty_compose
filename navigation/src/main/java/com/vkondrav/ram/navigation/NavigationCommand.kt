package com.vkondrav.ram.navigation

sealed class NavigationCommand {
    object NavigateUp : NavigationCommand()
    data class NavigateToRoute(val route: String) : NavigationCommand()
}
