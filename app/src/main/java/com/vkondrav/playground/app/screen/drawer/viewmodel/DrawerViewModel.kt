package com.vkondrav.playground.app.screen.drawer.viewmodel

import com.vkondrav.playground.app.base.item.ContentViewItem
import com.vkondrav.playground.app.base.viewmodel.BaseViewModel
import com.vkondrav.playground.app.base.viewmodel.ScreenEventViewModel
import com.vkondrav.playground.app.common.event.ScreenEvent
import com.vkondrav.playground.app.screen.drawer.composable.DrawerMenuViewItem
import com.vkondrav.playground.app.screen.drawer.domain.DrawerMenuItem
import com.vkondrav.playground.app.screen.drawer.usecase.DrawerMenuUseCase
import com.vkondrav.playground.app.screen.drawer.usecase.NavigateToRouteUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull

class DrawerViewModel(
    private val drawerMenuUseCase: DrawerMenuUseCase,
    private val navigateToRouteUseCase: NavigateToRouteUseCase,
    dispatcher: CoroutineDispatcher,
) : BaseViewModel(dispatcher), ScreenEventViewModel {

    private val _screenEvent = MutableStateFlow<ScreenEvent?>(null)
    override val screenEvent: Flow<ScreenEvent> = _screenEvent.filterNotNull()

    fun fetchMenu() {
        _screenEvent.value = ScreenEvent.Content(
            ContentViewItem(
                items = drawerMenuUseCase().viewItems,
            )
        )
    }

    private val List<DrawerMenuItem>.viewItems get() = map {
        DrawerMenuViewItem(
            title = it.title,
            onClickAction = {
                navigateToRouteUseCase(it.route)
            }
        )
    }
}
