package com.vkondrav.playground.app.snackbar

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.material.SnackbarHost
import androidx.compose.material.Text

@Composable
fun BoxScope.SnackbarHost(snackbarHostState: SnackbarHostState) {
    SnackbarHost(
        modifier = Modifier.align(Alignment.BottomCenter),
        hostState = snackbarHostState,
        snackbar = { snackbarData ->
            Card(
                backgroundColor = Color.LightGray,
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(2.dp, Color.LightGray),
                modifier = Modifier
                    .padding(16.dp)
                    .wrapContentSize()
            ) {
                Column(
                    modifier = Modifier.padding(8.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = "notification"
                    )
                    Text(text = snackbarData.message)
                }
            }
        }
    )
}
