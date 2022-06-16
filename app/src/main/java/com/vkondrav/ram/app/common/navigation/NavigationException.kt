package com.vkondrav.ram.app.common.navigation

data class NavigationException(override val message: String) :
    Throwable(message)
