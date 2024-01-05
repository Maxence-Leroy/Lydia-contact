package com.ragicorp.lydiacontact.contactDetailScreen.views

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import com.ragicorp.lydiacontact.R
import com.ragicorp.lydiacontact.ui.theme.LydiaContactTheme
import com.ragicorp.lydiacontact.ui.theme.Spacing

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PhoneNumberCard(
    phoneNumber: String
) {
    val clipboardManager: ClipboardManager = LocalClipboardManager.current
    val context = LocalContext.current

    ContactCard(
        title = stringResource(R.string.phoneNumber)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .combinedClickable(
                    onClick = {},
                    onLongClick = { clipboardManager.setText(AnnotatedString(phoneNumber)) }
                ),
            text = phoneNumber,
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(
                Spacing.single * 4,
                alignment = Alignment.CenterHorizontally
            )
        ) {
            FilledIconButton(onClick = {
                val dialIntent = Intent(Intent.ACTION_DIAL)
                dialIntent.data = Uri.parse("tel:$phoneNumber")
                ContextCompat.startActivity(context, dialIntent, null)
            }) {
                Icon(
                    Icons.Default.Phone,
                    contentDescription = stringResource(R.string.callDescription)
                )
            }
            FilledIconButton(onClick = {
                val smsIntent = Intent(Intent.ACTION_SENDTO)
                smsIntent.data = Uri.parse("smsto:$phoneNumber")
                ContextCompat.startActivity(context, smsIntent, null)
            }) {
                Icon(
                    Icons.Default.Send,
                    contentDescription = stringResource(R.string.messageDescription)
                )
            }
        }
    }
}

@Preview
@Composable
private fun AddressCardPreviewLight() {
    LydiaContactTheme(darkTheme = false) {
        PhoneNumberCard(phoneNumber = "+33123456789")
    }
}

@Preview
@Composable
private fun AddressCardPreviewDark() {
    LydiaContactTheme(darkTheme = true) {
        PhoneNumberCard(phoneNumber = "+33123456789")
    }
}
