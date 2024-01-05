package com.ragicorp.lydiacontact.contactListScreen.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.ragicorp.lydiacontact.R
import com.ragicorp.lydiacontact.ui.theme.LydiaContactTheme
import com.ragicorp.lydiacontact.ui.theme.Spacing

@Composable
fun FetchingError(
    retry: () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(Spacing.single * 2),
        color = MaterialTheme.colorScheme.secondaryContainer
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Spacing.single),
            verticalArrangement = Arrangement.spacedBy(Spacing.single),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.fetchingError),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.error
            )
            Button(onClick = retry) {
                Text(stringResource(id = R.string.retryButton))
            }
        }
    }
}

@Preview
@Composable
private fun FetchingErrorPreviewLight() {
    LydiaContactTheme(darkTheme = false) {
        FetchingError(retry = {})
    }
}

@Preview
@Composable
private fun FetchingErrorPreviewDark() {
    LydiaContactTheme(darkTheme = true) {
        FetchingError(retry = {})
    }
}