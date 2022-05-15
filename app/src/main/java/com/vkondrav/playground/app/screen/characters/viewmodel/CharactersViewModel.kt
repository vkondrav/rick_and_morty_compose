package com.vkondrav.playground.app.screen.characters.viewmodel

import com.vkondrav.playground.app.base.viewmodel.BaseViewModel
import com.vkondrav.playground.app.base.viewmodel.ScreenEventViewModel
import com.vkondrav.playground.app.common.composable.PageErrorViewItem
import com.vkondrav.playground.app.common.composable.PageLoadingViewItem
import com.vkondrav.playground.app.common.event.ScreenEvent
import com.vkondrav.playground.app.screen.characters.usecase.CharactersUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

class CharactersViewModel(
    private val charactersUseCase: CharactersUseCase,
    dispatcher: CoroutineDispatcher,
) : BaseViewModel(dispatcher), ScreenEventViewModel {

    private val _screenEvent = MutableStateFlow<ScreenEvent?>(null)
    override val screenEvent: Flow<ScreenEvent> = _screenEvent.filterNotNull()

    fun fetchCharacters() {
        launch {
            _screenEvent.value = ScreenEvent.Loading(PageLoadingViewItem)
            charactersUseCase(page = 0).getOrElse { error ->
                _screenEvent.value = ScreenEvent.Error(PageErrorViewItem(error))
                return@launch
            }.let { items ->
                _screenEvent.value = ScreenEvent.Column(items)
            }
        }
    }
}