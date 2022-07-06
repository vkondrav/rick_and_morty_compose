package com.vkondrav.ram.navigation.data

sealed class NavigationCommand {
    object NavigateUp : NavigationCommand()
    data class NavigateToRoute(val route: String) : NavigationCommand()
}
