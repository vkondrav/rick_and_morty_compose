package com.vkondrav.playground.app.characters.viewmodel

import androidx.compose.runtime.mutableStateListOf
import com.vkondrav.playground.app.base.item.ComposableAction
import com.vkondrav.playground.app.base.item.ComposableItem
import com.vkondrav.playground.app.base.viewmodel.BaseViewModel
import com.vkondrav.playground.app.characters.composable.CharacterViewItem
import com.vkondrav.playground.app.common.action.FetchDataAction
import com.vkondrav.playground.graphql.ram.RamRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class CharactersViewModel(
    private val ramRepository: RamRepository,
    dispatcher: CoroutineDispatcher,
) : BaseViewModel(dispatcher) {

    private val _columnItems = mutableStateListOf<ComposableItem>()
    override val columnItems: List<ComposableItem> = _columnItems

    private fun fetchData() {
        launch {
            val characters = ramRepository.fetchCharacters(page = 1).map {
                CharacterViewItem(it)
            }

            launchMain {
                _columnItems.clear()
                _columnItems.addAll(characters)
            }
        }
    }

    override fun onAction(action: ComposableAction) {
        when (action) {
            is FetchDataAction -> fetchData()
        }
    }
}