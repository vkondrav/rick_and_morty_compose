package com.vkondrav.playground.app.characters.composable

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vkondrav.playground.app.base.item.ComposableItem
import com.vkondrav.playground.app.base.item.OnComposableAction
import com.vkondrav.playground.graphql.ram.domain.RamCharacter

@Composable
fun CharacterView(item: CharacterViewItem, action: OnComposableAction) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = item.character.name,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        )
    }
}

data class CharacterViewItem(
    val character: RamCharacter,
) : ComposableItem {
    @Composable
    override fun Composable(action: OnComposableAction) = CharacterView(this, action)
}

@Preview
@Composable
private fun Preview() {
    CharacterViewItem(
        RamCharacter(
            name = "Morty",
        )
    ).Composable(action = { })
}