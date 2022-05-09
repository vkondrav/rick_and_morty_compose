package com.vkondrav.playground.app.common.event

sealed class DrawerEvent {
    object None: DrawerEvent()
    object Open: DrawerEvent()
    object Close: DrawerEvent()
}