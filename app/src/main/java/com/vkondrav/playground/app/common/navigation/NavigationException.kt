package com.vkondrav.playground.app.common.navigation

data class NavigationException(override val message: String) :
    Throwable(message)
