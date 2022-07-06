package com.vkondrav.ram.app.common.snackbar

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.vkondrav.ram.common.ui.design.DlsTheme
import com.vkondrav.ram.app.screen.main.usecase.HandleSnackbarStateUseCase
import org.koin.androidx.compose.get

@Composable
fun BoxScope.SnackbarHost(handleSnackBarStateUseCase: HandleSnackbarStateUseCase = get()) {
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect("snackbar") {
        handleSnackBarStateUseCase(snackbarHostState)
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
