package com.vkondrav.ram.app.snackbar

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import com.vkondrav.ram.app.design.DlsTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest

@Composable
fun BoxScope.SnackbarHost(snackbarMessages: Flow<String>) {
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(snackbarMessages) {
        snackbarMessages.collectLatest {
            snackbarHostState.currentSnackbarData?.dismiss()
            snackbarHostState.showSnackbar(it)
        }
    }

    SnackbarHost(
        modifier = Modifier.align(Alignment.BottomCenter),
        hostState = snackbarHostState,
        snackbar = { snackbarData ->
            Card(
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(2.dp, Color.LightGray),
                modifier = Modifier
                    .padding(16.dp)
                    .wrapContentSize(),
            ) {
                Column(
                    modifier = Modifier.padding(8.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    horizontalAlignment = CenterHorizontally,
                ) {
                    Icon(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = "notification",
                    )
                    Text(
                        modifier = Modifier.align(CenterHorizontally),
                        text = snackbarData.message,
                        style = DlsTheme.typography.label,
                        color = DlsTheme.colors.text,
                    )
                }
            }
        },
    )
}
