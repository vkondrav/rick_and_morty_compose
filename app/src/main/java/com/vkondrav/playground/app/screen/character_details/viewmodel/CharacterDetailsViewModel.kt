package com.vkondrav.playground.app.screen.character_details.viewmodel

import com.vkondrav.playground.app.base.viewmodel.BaseViewModel
import com.vkondrav.playground.app.base.viewmodel.ScreenEventViewModel
import com.vkondrav.playground.app.common.composable.PageErrorViewItem
import com.vkondrav.playground.app.common.composable.PageLoadingViewItem
import com.vkondrav.playground.app.common.event.ScreenEvent
import com.vkondrav.playground.app.screen.character_details.composable.CharacterDetailsViewItem
import com.vkondrav.playground.app.screen.character_details.usecase.FetchCharacterDetailsUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

class CharacterDetailsViewModel(
    private val fetchCharacterDetailsUseCase: FetchCharacterDetailsUseCase,
    dispatcher: CoroutineDispatcher,
) : BaseViewModel(dispatcher), ScreenEventViewModel {

    private val _screenEvent = MutableStateFlow<ScreenEvent?>(null)
    override val screenEvent: Flow<ScreenEvent> = _screenEvent.filterNotNull()

    fun fetchCharacterDetails(id: String) {
        launch {
            _screenEvent.value = ScreenEvent.Loading(PageLoadingViewItem)
            fetchCharacterDetailsUseCase(id)
                .onSuccess { details ->
                    _screenEvent.value = ScreenEvent.Content(
                        CharacterDetailsViewItem(
                            character = details.character,
                        )
                    )
                }
                .onFailure { error ->
                    _screenEvent.value = ScreenEvent.Error(PageErrorViewItem(error))
                }
        }
    }
}
