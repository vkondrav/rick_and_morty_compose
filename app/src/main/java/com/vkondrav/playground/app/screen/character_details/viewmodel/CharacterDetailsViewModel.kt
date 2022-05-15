package com.vkondrav.playground.app.screen.character_details.viewmodel

import com.vkondrav.playground.app.base.viewmodel.BaseViewModel
import com.vkondrav.playground.app.base.viewmodel.ScreenEventViewModel
import com.vkondrav.playground.app.common.composable.PageErrorViewItem
import com.vkondrav.playground.app.common.composable.PageLoadingViewItem
import com.vkondrav.playground.app.common.event.ScreenEvent
import com.vkondrav.playground.app.screen.character_details.usecase.CharacterDetailsUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

class CharacterDetailsViewModel(
    private val characterDetailsUseCase: CharacterDetailsUseCase,
    dispatcher: CoroutineDispatcher,
) : BaseViewModel(dispatcher), ScreenEventViewModel {

    private val _screenEvent = MutableStateFlow<ScreenEvent?>(null)
    override val screenEvent: Flow<ScreenEvent> = _screenEvent.filterNotNull()

    fun fetchCharacterDetails(id: String) {
        launch {
            _screenEvent.value = ScreenEvent.Loading(PageLoadingViewItem)
            characterDetailsUseCase(id)
                .onSuccess { items ->
                    _screenEvent.value = ScreenEvent.Column(items)
                }
                .onFailure { error ->
                    _screenEvent.value = ScreenEvent.Error(PageErrorViewItem(error))
                }
        }
    }
}