package com.ragicorp.lydiacontact.contactDetailScreen.views

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
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
fun AddressCard(
    address: String
) {
    val clipboardManager: ClipboardManager = LocalClipboardManager.current
    val context = LocalContext.current

    ContactCard(
        title = stringResource(R.string.addressTitle)
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
                        onLongClick = { clipboardManager.setText(AnnotatedString(address)) }
                    ),
                text = address,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Justify
            )

            FilledIconButton(onClick = {
                val mapIntent = Intent(Intent.ACTION_VIEW)
                mapIntent.data = Uri.parse("geo:geo:0,0?q=$address")
                ContextCompat.startActivity(context, mapIntent, null)
            }) {
                Icon(
                    Icons.Default.LocationOn,
                    contentDescription = stringResource(R.string.mapDescription)
                )
            }
        }
    }
}

@Preview
@Composable
private fun AddressCardPreviewLight() {
    LydiaContactTheme(darkTheme = false) {
        AddressCard(address = "55 Rue du Faubourg Saint-Honoré\n75008 Paris")
    }
}

@Preview
@Composable
private fun AddressCardPreviewDark() {
    LydiaContactTheme(darkTheme = true) {
        AddressCard(address = "55 Rue du Faubourg Saint-Honoré\n75008 Paris")
    }
}