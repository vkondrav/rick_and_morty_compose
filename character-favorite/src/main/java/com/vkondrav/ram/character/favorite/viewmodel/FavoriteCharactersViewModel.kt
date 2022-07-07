package com.vkondrav.ram.character.favorite.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.vkondrav.ram.common.ui.view.ComposableItem
import com.vkondrav.ram.common.ui.viewmodel.BaseViewModel
import com.vkondrav.ram.common.ui.data.ScreenState
import com.vkondrav.ram.common.ui.viewmodel.ScreenStateViewModel
import com.vkondrav.ram.common.ui.view.PageErrorViewItem
import com.vkondrav.ram.character.favorite.usecase.FetchFavoriteCharactersUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class FavoriteCharactersViewModel(
    private val favoriteCharactersUseCase: FetchFavoriteCharactersUseCase,
    dispatcher: CoroutineDispatcher,
) : BaseViewModel(dispatcher), ScreenStateViewModel {

    private var _screenState = mutableStateOf<ScreenState>(ScreenState.Loading())
    override var screenState: State<ScreenState> = _screenState

    override val items: StateFlow<List<ComposableItem>>
        get() = favoriteCharactersUseCase().getOrElse {
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
