package com.ragicorp.lydiacontact.contactDetailScreen.views

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.ragicorp.lydiacontact.R
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatter.ofPattern
import java.util.Locale

@Composable
fun DateOfBirthCard(
    dateOfBirth: Instant
) {
    ContactCard(
        title = stringResource(R.string.dateOfBirthTitle)
    ) {
        Text(
            text = dateToString(dateOfBirth),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Justify
        )
    }
}

fun dateToString(date: Instant): String {
    val zonedDate = date.atZone(ZoneId.systemDefault())
    val formatter: DateTimeFormatter = ofPattern("dd MMMM yyyy kk:mm:ss")
        .withLocale(Locale.getDefault())
        .withZone(ZoneId.systemDefault())
    return formatter.format(zonedDate)
}

