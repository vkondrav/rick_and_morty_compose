package com.vkondrav.playground.app.main.viewmodel

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vkondrav.playground.app.base.item.OnComposableAction

@Composable
fun BoxScope.BottomSheetButton(onComposableAction: OnComposableAction) {
    Button(
        modifier = Modifier
            .align(Alignment.BottomEnd)
            .padding(16.dp),
        onClick = { onComposableAction(OpenBottomSheet) }
    ) {
        Text(text = "Bottom Sheet")
    }
}