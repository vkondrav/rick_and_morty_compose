package com.vkondrav.playground.app.common.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidViewBinding
import com.vkondrav.playground.app.base.item.ComposableItem
import com.vkondrav.playground.app.base.item.OnComposableAction
import com.vkondrav.playground.app.databinding.ItemXmlLayoutBinding
import com.vkondrav.playground.app.common.action.ViewBindingAction

@Composable
fun ViewBindingCard(item: ViewBindingCardItem, action: OnComposableAction) {
    AndroidViewBinding(ItemXmlLayoutBinding::inflate) {
        textView.text = item.text
        textView.setOnClickListener {
            action.invoke(ViewBindingAction)
        }
    }
}

@Preview
@Composable
private fun Preview() {
    ViewBindingCardItem("I am text").Composable(action = { })
}

data class ViewBindingCardItem(val text: String) : ComposableItem {
    @Composable
    override fun Composable(action: OnComposableAction) =
        ViewBindingCard(this, action)
}