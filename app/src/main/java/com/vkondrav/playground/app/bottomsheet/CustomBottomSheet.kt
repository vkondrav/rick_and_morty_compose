package com.vkondrav.playground.app.bottomsheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CustomBottomSheet(
    bottomSheetScaffoldState: BottomSheetScaffoldState,
    content: @Composable (PaddingValues) -> Unit,
) {
    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetShape = RoundedCornerShape(
            bottomStart = 0.dp,
            bottomEnd = 0.dp,
            topStart = 12.dp,
            topEnd = 12.dp,
        ),
        sheetContent = {
            Column(
                Modifier
                    .background(Color.Cyan)
                    .heightIn(min = 100.dp, max = 500.dp)
            ) {
                //TODO: add composable
            }
        },
        sheetElevation = 8.dp,
        content = content,
    )
}