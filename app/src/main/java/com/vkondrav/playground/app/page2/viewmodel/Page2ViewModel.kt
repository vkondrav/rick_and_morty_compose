package com.vkondrav.playground.app.page2.viewmodel

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.PersonAdd
import androidx.compose.runtime.mutableStateListOf
import androidx.navigation.NavController
import com.vkondrav.playground.app.base.item.ComposableAction
import com.vkondrav.playground.app.base.item.ComposableItem
import com.vkondrav.playground.app.base.viewmodel.BaseViewModel
import com.vkondrav.playground.app.common.action.ImageCardAction
import com.vkondrav.playground.app.common.composable.ImageCardItem
import com.vkondrav.playground.app.common.navigation.Route

class Page2ScreenViewModel(private val navController: NavController) :
    BaseViewModel() {

    private val _columnItems = mutableStateListOf<ComposableItem>()
    override val columnItems: List<ComposableItem> = _columnItems

    override fun fetchData() {
        _columnItems.clear()
        (1..1_000).forEach { i ->
            mutableListOf<ComposableItem>().apply {
                _columnItems.add(
                    ImageCardItem(
                        image1Title = "Person $i-a",
                        image1 = Icons.Rounded.PersonAdd,
                        image2Title = "Person $i-b",
                        image2 = Icons.Rounded.Person,
                    )
                )
            }
        }
    }

    override fun onAction(action: ComposableAction) {
        when (action) {
            is ImageCardAction -> navController.navigate(Route.Screen3)
        }
    }
}