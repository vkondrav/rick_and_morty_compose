package com.vkondrav.playground.app.characters.viewmodel

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.vkondrav.playground.app.base.item.ComposableAction
import com.vkondrav.playground.app.base.item.ComposableItem
import com.vkondrav.playground.app.base.viewmodel.BaseViewModel
import com.vkondrav.playground.app.base.viewmodel.OnActionViewModel
import com.vkondrav.playground.app.base.viewmodel.ScreenEventViewModel
import com.vkondrav.playground.app.characters.composable.CharacterView
import com.vkondrav.playground.app.characters.composable.CharacterViewItem
import com.vkondrav.playground.app.common.action.FetchDataAction
import com.vkondrav.playground.app.common.composable.PageErrorViewItem
import com.vkondrav.playground.app.common.composable.PageLoadingViewItem
import com.vkondrav.playground.app.common.event.ScreenEvent
import com.vkondrav.playground.app.page1.source.CharacterSource
import com.vkondrav.playground.graphql.ram.RamRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

class CharactersViewModel(
    private val ramRepository: RamRepository,
    dispatcher: CoroutineDispatcher,
) : BaseViewModel(dispatcher), ScreenEventViewModel, OnActionViewModel {

    private val _screenEvent = MutableStateFlow<ScreenEvent?>(null)
    override val screenEvent: Flow<ScreenEvent> = _screenEvent.filterNotNull()

    private fun fetchData() {
        launch {
            _screenEvent.value = ScreenEvent.Loading(PageLoadingViewItem)
            ramRepository.fetchCharacters(0).getOrElse { error ->
                _screenEvent.value = ScreenEvent.Error(PageErrorViewItem(error))
                return@launch
            }.run {
                _screenEvent.value = ScreenEvent.Column(this.map { CharacterViewItem(it) })
            }
        }
    }

    override fun onAction(action: ComposableAction) {
        when (action) {
            is FetchDataAction -> fetchData()
        }
    }
}