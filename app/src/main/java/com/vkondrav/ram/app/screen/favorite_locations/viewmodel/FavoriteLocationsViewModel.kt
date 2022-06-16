package com.vkondrav.ram.app.screen.favorite_locations.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.vkondrav.ram.app.base.item.ComposableItem
import com.vkondrav.ram.app.base.viewmodel.BaseViewModel
import com.vkondrav.ram.app.base.viewmodel.ScreenState
import com.vkondrav.ram.app.base.viewmodel.ScreenStateViewModel
import com.vkondrav.ram.app.common.composable.PageErrorViewItem
import com.vkondrav.ram.app.screen.favorite_locations.usecase.FetchFavoriteLocationsUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class FavoriteLocationsViewModel(
    private val favoriteLocationsUseCase: FetchFavoriteLocationsUseCase,
    dispatcher: CoroutineDispatcher,
) : BaseViewModel(dispatcher), ScreenStateViewModel {

    private var _screenState = mutableStateOf<ScreenState>(ScreenState.Loading())
    override var screenState: State<ScreenState> = _screenState

    override val items: StateFlow<List<ComposableItem>>
        get() = favoriteLocationsUseCase().getOrElse {
            _screenState.value = ScreenState.Error(PageErrorViewItem(it))
            emptyFlow()
        }.map { items ->

            if (items.isEmpty()) {
                _screenState.value = ScreenState.Error(PageErrorViewItem(Exception("No data")))
            } else {
                _screenState.value = ScreenState.Content
            }

            items
        }.distinctUntilChanged().stateIn(
            scope = this,
            started = SharingStarted.WhileSubscribed(),
            initialValue = emptyList(),
        )
}
