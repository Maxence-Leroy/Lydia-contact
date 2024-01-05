package com.ragicorp.lydiacontact.contactListScreen.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.ImageRequest
import com.ragicorp.lydiacontact.R
import com.ragicorp.lydiacontact.libcontact.ContactDomain
import com.ragicorp.lydiacontact.libcontact.stubContact
import com.ragicorp.lydiacontact.ui.theme.LydiaContactTheme
import com.ragicorp.lydiacontact.ui.theme.Spacing
import com.ragicorp.lydiacontact.utils.nationalCodeToEmoji

private val imageSize = 48.dp

@Composable
internal fun ContactItem(
    contact: ContactDomain,
    onPress: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        onClick = onPress
    ) {
        Row(
            modifier = Modifier.padding(horizontal = Spacing.single * 2, vertical = Spacing.single),
            horizontalArrangement = Arrangement.spacedBy(Spacing.single * 2),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SubcomposeAsyncImage(
                modifier = Modifier
                    .size(imageSize)
                    .clip(CircleShape),
                model = ImageRequest.Builder(LocalContext.current).data(contact.picture.thumbnail)
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

            Column {
                Text(
                    text = "${nationalCodeToEmoji(contact.nat)} ${contact.title} ${contact.firstName} ${contact.lastName}",
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = contact.email,
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }
    }
}

@Preview
@Composable
private fun ContactItemPreviewLight() {
    LydiaContactTheme(darkTheme = false) {
        ContactItem(contact = stubContact, onPress = {})
    }
}

@Preview
@Composable
private fun ContactItemPreviewDark() {
    LydiaContactTheme(darkTheme = true) {
        ContactItem(contact = stubContact, onPress = {})
    }
}