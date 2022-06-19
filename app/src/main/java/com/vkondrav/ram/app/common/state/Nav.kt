package com.vkondrav.ram.app.common.state

sealed class Nav {
    class Route(val destination: String) : Nav()
    object Back : Nav() {
        override fun hashCode(): Int {
            return super.hashCode()
        }
        override fun equals(other: Any?) = false
    }
}
