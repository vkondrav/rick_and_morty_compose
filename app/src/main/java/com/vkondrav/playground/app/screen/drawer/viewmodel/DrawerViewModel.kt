package com.vkondrav.playground.app.screen.drawer.viewmodel

import com.vkondrav.playground.app.base.viewmodel.BaseViewModel
import com.vkondrav.playground.app.base.viewmodel.ScreenEventViewModel
import com.vkondrav.playground.app.common.event.ScreenEvent
import com.vkondrav.playground.app.screen.drawer.usecase.DrawerMenuUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull

class DrawerViewModel(
    private val drawerMenuUseCase: DrawerMenuUseCase,
    dispatcher: CoroutineDispatcher,
) : BaseViewModel(dispatcher), ScreenEventViewModel {

    private val _screenEvent = MutableStateFlow<ScreenEvent?>(null)
    override val screenEvent: Flow<ScreenEvent> = _screenEvent.filterNotNull()

    fun fetchMenu() {
        _screenEvent.value = ScreenEvent.Column(drawerMenuUseCase())
    }
}