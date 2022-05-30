package com.vkondrav.playground.app.screen.characters.viewmodel

import com.vkondrav.playground.app.base.item.ContentViewItem
import com.vkondrav.playground.app.base.viewmodel.BaseViewModel
import com.vkondrav.playground.app.base.viewmodel.ScreenEventViewModel
import com.vkondrav.playground.app.common.composable.PageErrorViewItem
import com.vkondrav.playground.app.common.composable.PageLoadingViewItem
import com.vkondrav.playground.app.common.event.ScreenEvent
import com.vkondrav.playground.app.screen.characters.composable.CharacterViewItem
import com.vkondrav.playground.app.screen.characters.usecase.FetchCharactersUseCase
import com.vkondrav.playground.app.screen.characters.usecase.NavigateToCharacterDetailsUseCase
import com.vkondrav.playground.graphql.ram.domain.RamCharacter
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

class CharactersViewModel(
    private val fetchCharactersUseCase: FetchCharactersUseCase,
    private val navigateToCharacterDetailsUseCase: NavigateToCharacterDetailsUseCase,
    dispatcher: CoroutineDispatcher,
) : BaseViewModel(dispatcher), ScreenEventViewModel {

    private val _screenEvent = MutableStateFlow<ScreenEvent?>(null)
    override val screenEvent: Flow<ScreenEvent> = _screenEvent.filterNotNull()

    fun fetchCharacters() {
        launch {
            _screenEvent.value = ScreenEvent.Loading(PageLoadingViewItem)
            fetchCharactersUseCase(page = 0).getOrElse { error ->
                _screenEvent.value = ScreenEvent.Error(PageErrorViewItem(error))
                return@launch
            }.let { characters ->
                _screenEvent.value = ScreenEvent.Content(
                    ContentViewItem(
                        items = characters.viewItems,
                    )
                )
            }
        }
    }

    private val List<RamCharacter>.viewItems
        get() = map { character ->
            CharacterViewItem(
                character = character,
                onClickAction = {
                    navigateToCharacterDetailsUseCase(
                        id = character.id,
                        title = character.name,
                    )
                },
            )
        }
}
