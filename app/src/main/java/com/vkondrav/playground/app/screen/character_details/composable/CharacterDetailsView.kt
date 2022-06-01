package com.vkondrav.playground.app.screen.character_details.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vkondrav.playground.app.base.item.ComposableItem
import com.vkondrav.playground.app.common.composable.Avatar
import com.vkondrav.playground.graphql.ram.domain.RamCharacter

@Composable
fun CharacterDetailsView(
    item: CharacterDetailsViewItem,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
    ) {
        Avatar(
            source = item.character.image,
            sizeVariant = Avatar.SizeVariant.XXLarge,
            modifier = Modifier
                .align(CenterHorizontally)
                .padding(8.dp),
        )
    }
}

data class CharacterDetailsViewItem(
    val character: RamCharacter,
) : ComposableItem {

    @Composable
    override fun Composable() = CharacterDetailsView(this)

}

@Preview
@Composable
private fun Preview() {
    CharacterDetailsViewItem(
        RamCharacter(
            id = "1",
            name = "Morty",
            status = null,
            species = null,
            image = null,
        ),
    ).Composable()
}