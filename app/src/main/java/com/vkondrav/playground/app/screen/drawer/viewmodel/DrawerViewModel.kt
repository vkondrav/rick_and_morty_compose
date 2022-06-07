package com.vkondrav.playground.app.screen.drawer.viewmodel

import androidx.compose.runtime.mutableStateOf
import com.vkondrav.playground.app.base.item.ComposableItem
import com.vkondrav.playground.app.base.viewmodel.BaseViewModel
import com.vkondrav.playground.app.base.viewmodel.ScreenState
import com.vkondrav.playground.app.base.viewmodel.ScreenStateViewModel
import com.vkondrav.playground.app.screen.drawer.usecase.DrawerMenuSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DrawerViewModel(
    private val drawerMenuSource: DrawerMenuSource,
    dispatcher: CoroutineDispatcher,
) : BaseViewModel(dispatcher), ScreenStateViewModel {

    override var screenState = mutableStateOf<ScreenState>(ScreenState.Loading())

    override val items: Flow<List<ComposableItem>>
        get() = drawerMenuSource().map { drawerMenu ->
            screenState.value = ScreenState.Content
            drawerMenu
        }

}
