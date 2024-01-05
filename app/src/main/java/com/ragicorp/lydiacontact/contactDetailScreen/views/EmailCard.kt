package com.ragicorp.lydiacontact.contactDetailScreen.views

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
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
fun EmailCard(
    email: String
) {
    val clipboardManager: ClipboardManager = LocalClipboardManager.current
    val context = LocalContext.current

    ContactCard(
        title = stringResource(R.string.email)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(Spacing.single * 2)
        ) {
            Text(
                modifier = Modifier
                    .weight(1f, fill = true)
                    .combinedClickable(
                        onClick = {},
                        onLongClick = { clipboardManager.setText(AnnotatedString(email)) }
                    ),
                text = email,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )
            FilledIconButton(
                onClick = {
                    val emailIntent = Intent(Intent.ACTION_SENDTO)
                    emailIntent.data = Uri.parse("mailto:$email")
                    ContextCompat.startActivity(context, emailIntent, null)
                }
            ) {
                Icon(
                    Icons.Default.Email,
                    contentDescription = stringResource(R.string.emailDescription)
                )
            }
        }
    }
}

@Preview
@Composable
private fun EmailCardPreviewLight() {
    LydiaContactTheme(darkTheme = false) {
        EmailCard(email = "harry.potter@hogwarts.edu")
    }
}

@Preview
@Composable
private fun EmailCardPreviewDark() {
    LydiaContactTheme(darkTheme = true) {
        EmailCard(email = "harry.potter@hogwarts.edu")
    }
}
