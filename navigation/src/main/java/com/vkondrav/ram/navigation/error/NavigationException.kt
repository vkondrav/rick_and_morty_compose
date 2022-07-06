package com.vkondrav.ram.navigation.error

data class NavigationException(override val message: String) :
    Throwable(message)
