package com.vkondrav.playground.app.characters.viewmodel

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.vkondrav.playground.app.base.item.ComposableAction
import com.vkondrav.playground.app.base.item.ComposableItem
import com.vkondrav.playground.app.base.viewmodel.BaseViewModel
import com.vkondrav.playground.app.base.viewmodel.OnActionViewModel
import com.vkondrav.playground.app.common.action.FetchDataAction
import com.vkondrav.playground.app.page1.source.CharacterSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

class CharactersViewModel(
    private val characterSource: CharacterSource,
    dispatcher: CoroutineDispatcher,
) : BaseViewModel(dispatcher), OnActionViewModel {

    fun fetchData(): Flow<PagingData<ComposableItem>>
        = Pager(PagingConfig(50)) { characterSource }.flow

    override fun onAction(action: ComposableAction) {
        when (action) {
            is FetchDataAction -> fetchData()
        }
    }
}