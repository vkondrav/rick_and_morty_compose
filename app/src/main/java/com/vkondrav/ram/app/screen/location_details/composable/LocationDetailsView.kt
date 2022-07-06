package com.vkondrav.ram.app.screen.location_details.composable

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
import com.vkondrav.ram.common.ui.view.ComposableItem
import com.vkondrav.ram.domain.RamLocation
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun LocationDetailsView(
    item: LocationDetailsViewItem,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
    ) {
        Icon(
            imageVector = Icons.Default.AccountCircle,
            contentDescription = "",
            modifier = Modifier
                .width(48.dp)
                .height(48.dp)
                .align(CenterHorizontally),
        )
        Text(
            text = item.location.name,
            modifier = Modifier
                .wrapContentSize()
                .wrapContentHeight()
                .align(CenterHorizontally),
        )
    }
}

data class LocationDetailsViewItem(
    val location: RamLocation,
) : ComposableItem {

    @Composable
    override fun Composable() = LocationDetailsView(this)

}

@Preview
@Composable
private fun Preview() {
    LocationDetailsViewItem(
        RamLocation(
            id = "1",
            name = "Morty",
            dimension = null,
            isFavorite = emptyFlow(),
        ),
    ).Composable()
}
