package com.vkondrav.playground.app.screen.locations.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vkondrav.playground.app.base.item.ComposableItem
import com.vkondrav.playground.graphql.ram.domain.RamLocation

@Composable
fun LocationView(item: LocationViewItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                item.onClickAction()
            }
    ) {
        Text(
            text = item.location.name,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        )
    }
}

data class LocationViewItem(
    val location: RamLocation,
    val onClickAction: () -> Unit,
) : ComposableItem {
    @Composable
    override fun Composable() = LocationView(this)
}

@Preview
@Composable
private fun Preview() {
    LocationViewItem(
        RamLocation(
            id = "0",
            name = "Morty",
            dimension = "C-137"
        ),
        onClickAction = { },
    ).Composable()
}