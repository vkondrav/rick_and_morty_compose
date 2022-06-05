package com.vkondrav.playground.app.screen.characters.viewmodel

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.vkondrav.playground.app.base.viewmodel.PagingViewModel
import com.vkondrav.playground.app.base.item.ComposableItem
import com.vkondrav.playground.app.base.item.ContentViewItem
import com.vkondrav.playground.app.base.viewmodel.BaseViewModel
import com.vkondrav.playground.app.base.viewmodel.ScreenEventViewModel
import com.vkondrav.playground.app.common.composable.PageErrorViewItem
import com.vkondrav.playground.app.common.event.ScreenEvent
import com.vkondrav.playground.app.screen.characters.usecase.CharactersSource
import com.vkondrav.playground.app.screen.characters.usecase.FetchCharactersUseCase
import com.vkondrav.playground.app.screen.characters.usecase.TransformCharactersUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

class CharactersViewModel(
    private val fetchCharactersUseCase: FetchCharactersUseCase,
    private val transformCharactersUseCase: TransformCharactersUseCase,
    private val charactersSource: CharactersSource,
    dispatcher: CoroutineDispatcher,
) : BaseViewModel(dispatcher), ScreenEventViewModel, PagingViewModel {

    private val _screenEvent = MutableStateFlow<ScreenEvent?>(null)
    override val screenEvent: Flow<ScreenEvent> = _screenEvent.filterNotNull()

    fun fetchCharacters() {
        launch {
            _screenEvent.value = ScreenEvent.Loading()
            fetchCharactersUseCase(page = 0).getOrElse { error ->
                _screenEvent.value = ScreenEvent.Error(PageErrorViewItem(error))
                return@launch
            }.let { characters ->
                _screenEvent.value = ScreenEvent.Content(
                    ContentViewItem(
                        items = transformCharactersUseCase(characters.items),
                    ),
                )
            }
        }
    }

    override val pagingData: Flow<PagingData<ComposableItem>> = Pager(PagingConfig(pageSize = 1)) {
        charactersSource
    }.flow.cachedIn(this)
}
