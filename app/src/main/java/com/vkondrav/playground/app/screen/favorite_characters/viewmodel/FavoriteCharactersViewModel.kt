package com.vkondrav.playground.app.screen.favorite_characters.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.vkondrav.playground.app.base.item.ComposableItem
import com.vkondrav.playground.app.base.viewmodel.BaseViewModel
import com.vkondrav.playground.app.common.composable.PageErrorViewItem
import com.vkondrav.playground.app.common.composable.PageLoadingViewItem
import com.vkondrav.playground.app.screen.favorite_characters.usecase.FetchFavoriteCharactersUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.map

sealed class ScreenState {
    data class Loading(val item: ComposableItem = PageLoadingViewItem) : ScreenState()
    data class Error(val item: ComposableItem) : ScreenState()
    object Content : ScreenState()
}

interface ScreenStateViewModel {
    val screenState: MutableState<ScreenState>
    val items: Flow<List<ComposableItem>>
}

class FavoriteCharactersViewModel(
    private val favoriteCharactersUseCase: FetchFavoriteCharactersUseCase,
    dispatcher: CoroutineDispatcher,
) : BaseViewModel(dispatcher), ScreenStateViewModel {

    override var screenState = mutableStateOf<ScreenState>(ScreenState.Loading())

    override val items: Flow<List<ComposableItem>>
        get() = favoriteCharactersUseCase().getOrElse {
            screenState.value = ScreenState.Error(PageErrorViewItem(it))
            emptyFlow()
        }.map { items ->

            if (items.isEmpty()) {
                screenState.value = ScreenState.Error(PageErrorViewItem(Exception("No data")))
            } else {
                screenState.value = ScreenState.Content
            }

            items
        }
}
