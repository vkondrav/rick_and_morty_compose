package com.vkondrav.playground.app.screen.character_details.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vkondrav.playground.app.base.item.ComposableItem
import com.vkondrav.playground.graphql.ram.domain.RamCharacter

@Composable
fun CharacterDetailsView(
    item: CharacterDetailsViewItem,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Icon(
            imageVector = Icons.Default.AccountCircle,
            contentDescription = "",
            modifier = Modifier
                .width(48.dp)
                .height(48.dp)
                .align(CenterHorizontally)
        )
        Text(
            text = item.character.name,
            modifier = Modifier
                .wrapContentSize()
                .wrapContentHeight()
                .align(CenterHorizontally)
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
        )
    ).Composable()
}
