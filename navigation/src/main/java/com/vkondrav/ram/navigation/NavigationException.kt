package com.vkondrav.ram.navigation

data class NavigationException(override val message: String) :
    Throwable(message)
