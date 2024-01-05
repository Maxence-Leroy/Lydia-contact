package com.ragicorp.lydiacontact.contactListScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ragicorp.lydiacontact.contactListScreen.views.ContactItem
import com.ragicorp.lydiacontact.contactListScreen.views.FetchingError
import com.ragicorp.lydiacontact.ui.theme.Spacing
import org.koin.androidx.compose.koinViewModel

internal object ContactList {
    const val Route = "contactList"

    @Composable
    fun Screen(
        contactListViewModel: ContactListViewModel = koinViewModel(),
        navigateToContactDetail: (String) -> Unit
    ) {
        val contacts =
            contactListViewModel.contacts.collectAsStateWithLifecycle()
        val fetchingState =
            contactListViewModel.fetchingState.collectAsStateWithLifecycle()
        val lazyListState = rememberLazyListState()
        val itemOffsetBeforeFetching = 3
        val shouldTriggerFetch: Boolean by remember {
            derivedStateOf {
                (lazyListState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
                    ?: 0) >= contacts.value.size - itemOffsetBeforeFetching
            }
        }

        LaunchedEffect(shouldTriggerFetch) {
            if (shouldTriggerFetch) {
                contactListViewModel.fetchMoreContacts()
            }
        }
        Scaffold { padding ->
            Surface(modifier = Modifier.padding(padding)) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    state = lazyListState,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(contacts.value) {
                        ContactItem(
                            contact = it,
                            onPress = { navigateToContactDetail(it.id.toString()) })
                    }
                    item {
                        Box(modifier = Modifier
                            .height(100.dp)
                            .padding(Spacing.single)) {
                            when (fetchingState.value) {
                                FetchingState.LOADING -> CircularProgressIndicator()
                                FetchingState.ERROR -> FetchingError(retry = { contactListViewModel.fetchMoreContacts() })
                                else -> {}
                            }
                        }
                    }
                }
            }
        }
    }
}