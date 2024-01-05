package com.ragicorp.lydiacontact.contactListScreen.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ragicorp.lydiacontact.libcontact.ContactDomain
import com.ragicorp.lydiacontact.libcontact.stubContact
import com.ragicorp.lydiacontact.ui.theme.LydiaContactTheme
import com.ragicorp.lydiacontact.ui.theme.Spacing
import com.ragicorp.lydiacontact.ui.views.ContactImage
import com.ragicorp.lydiacontact.ui.views.ImageSize

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
            ContactImage(size = ImageSize.SMALL, url = contact.picture.thumbnail)

            Column {
                Text(
                    text = contact.textToShow,
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