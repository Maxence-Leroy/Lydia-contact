package com.ragicorp.lydiacontact.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.ImageRequest
import com.ragicorp.lydiacontact.R

enum class ImageSize(val sizeInDp: Dp) {
    SMALL(48.dp),
    LARGE(128.dp)
}

@Composable
fun ContactImage(
    size: ImageSize,
    url: String
) {
    SubcomposeAsyncImage(
        modifier = Modifier
            .size(size.sizeInDp)
            .clip(CircleShape),
        model = ImageRequest.Builder(LocalContext.current).data(url)
            .build(),
        contentDescription = stringResource(R.string.thumbnail)
    ) {
        when (painter.state) {
            is AsyncImagePainter.State.Success -> SubcomposeAsyncImageContent()
            is AsyncImagePainter.State.Error -> Image(
                painter = painterResource(R.drawable.error_image),
                contentDescription = null
            )

            else -> CircularProgressIndicator()
        }
    }
}