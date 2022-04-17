package com.vkondrav.playground.app.home.viewmodel

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.PersonAdd
import androidx.compose.runtime.mutableStateListOf
import com.vkondrav.playground.app.common.item.ComposableItem
import com.vkondrav.playground.app.home.action.MessageCardAction
import com.vkondrav.playground.app.common.item.ComposableAction
import com.vkondrav.playground.app.common.viewmodel.BaseViewModel
import com.vkondrav.playground.app.home.action.ImageCardAction
import com.vkondrav.playground.app.home.composable.ImageCardItem
import com.vkondrav.playground.app.home.composable.MessageCardItem

class HomeScreenViewModel : BaseViewModel() {

    private val _columnItems = mutableStateListOf<ComposableItem>()
    override val columnItems: List<ComposableItem> = _columnItems

    override fun fetchData() {
        _columnItems.clear()
        (0..1_000).forEach { i ->
            mutableListOf<ComposableItem>().apply {
                if (i % 2 == 0) {
                    MessageCardItem(
                        message = "Item $i",
                        image = Icons.Default.AccountCircle
                    )
                } else {
                    ImageCardItem(
                        image1Title = "Person $i-a",
                        image1 = Icons.Rounded.PersonAdd,
                        image2Title = "Person $i-b",
                        image2 = Icons.Rounded.Person,
                    )
                }.run {
                    _columnItems.add(this)
                }
            }
        }
    }

    override fun onAction(action: ComposableAction) {
        when (action) {
            is MessageCardAction -> "Add Item Action"
            is ImageCardAction -> "Add Icon Action ${action.imageName}"
            else -> null
        }?.run {
            Log.i("HomeScreen", this)
        }
    }
}