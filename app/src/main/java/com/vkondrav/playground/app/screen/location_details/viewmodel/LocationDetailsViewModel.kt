package com.vkondrav.playground.app.screen.location_details.viewmodel

import androidx.compose.runtime.mutableStateOf
import com.vkondrav.playground.app.base.item.ComposableItem
import com.vkondrav.playground.app.base.viewmodel.BaseViewModel
import com.vkondrav.playground.app.base.viewmodel.ScreenState
import com.vkondrav.playground.app.base.viewmodel.ScreenStateViewModel
import com.vkondrav.playground.app.common.composable.PageErrorViewItem
import com.vkondrav.playground.app.screen.location_details.usecase.LocationDetailsSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.map

class LocationDetailsViewModel(
    private val locationId: String,
    private val locationDetailsSource: LocationDetailsSource,
    dispatcher: CoroutineDispatcher,
) : BaseViewModel(dispatcher), ScreenStateViewModel {

    override var screenState = mutableStateOf<ScreenState>(ScreenState.Loading())

    override val items: Flow<List<ComposableItem>>
        get() = locationDetailsSource(locationId).getOrElse {
            screenState.value = ScreenState.Error(PageErrorViewItem(it))
            emptyFlow()
        }.catch {
            screenState.value = ScreenState.Error(PageErrorViewItem(it))
        }.map { characterDetails ->
            screenState.value = ScreenState.Content
            characterDetails
        }
}
