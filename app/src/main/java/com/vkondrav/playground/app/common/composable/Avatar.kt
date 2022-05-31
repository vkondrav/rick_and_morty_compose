package com.vkondrav.playground.app.common.composable

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.ImageRequest
import com.vkondrav.playground.app.design.DlsTheme

object Avatar {
    sealed class SizeVariant {
        @get:Composable
        abstract val value: Dp

        object Small : SizeVariant() {
            override val value: Dp
                @Composable
                get() = DlsTheme.sizes.medium
        }

        object Medium : SizeVariant() {
            override val value: Dp
                @Composable
                get() = DlsTheme.sizes.large
        }

        object Large : SizeVariant() {
            override val value: Dp
                @Composable
                get() = DlsTheme.sizes.larger
        }
    }
}

@Composable
fun Avatar(
    source: String?,
    sizeVariant: Avatar.SizeVariant,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .size(sizeVariant.value)
            .clip(CircleShape)
            .border(
                DlsTheme.sizes.smaller,
                DlsTheme.colors.primary,
                CircleShape,
            ),
    ) {
        SubcomposeAsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(source)
                .crossfade(true)
                .build(),
            contentDescription = null,
        ) {
            val state = painter.state
            if (state is AsyncImagePainter.State.Error) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    modifier = Modifier.fillMaxSize(),
                    contentDescription = null,
                )
            } else {
                SubcomposeAsyncImageContent()
            }
        }
    }
}
