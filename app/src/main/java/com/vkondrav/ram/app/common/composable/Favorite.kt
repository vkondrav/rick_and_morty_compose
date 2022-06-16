package com.vkondrav.ram.app.common.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarOutline
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RowScope.Favorite(favorite: Boolean, onClickAction: (Boolean) -> Unit) {
    val starIcon = if (favorite) Icons.Default.Star else Icons.Default.StarOutline

    Icon(
        imageVector = starIcon,
        contentDescription = null,
        modifier = Modifier
            .align(Alignment.CenterVertically)
            .padding(end = 8.dp)
            .size(32.dp)
            .clickable {
                onClickAction(!favorite)
            },
    )
}
