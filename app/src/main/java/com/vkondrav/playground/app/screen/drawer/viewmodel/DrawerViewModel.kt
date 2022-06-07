package com.vkondrav.playground.app.screen.drawer.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.vkondrav.playground.app.base.item.ComposableItem
import com.vkondrav.playground.app.base.viewmodel.BaseViewModel
import com.vkondrav.playground.app.base.viewmodel.ScreenState
import com.vkondrav.playground.app.base.viewmodel.ScreenStateViewModel
import com.vkondrav.playground.app.screen.drawer.usecase.DrawerMenuSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class DrawerViewModel(
    private val drawerMenuSource: DrawerMenuSource,
    dispatcher: CoroutineDispatcher,
) : BaseViewModel(dispatcher), ScreenStateViewModel {

    private var _screenState = mutableStateOf<ScreenState>(ScreenState.Loading())
    override val screenState: State<ScreenState> = _screenState

    override val items: StateFlow<List<ComposableItem>>
        get() = drawerMenuSource().map { drawerMenu ->
            _screenState.value = ScreenState.Content
            drawerMenu
        }.distinctUntilChanged().stateIn(
            scope = this,
            started = SharingStarted.WhileSubscribed(),
            initialValue = emptyList(),
        )

}
