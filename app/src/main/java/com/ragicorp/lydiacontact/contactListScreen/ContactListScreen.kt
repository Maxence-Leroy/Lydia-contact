package com.ragicorp.lydiacontact.contactListScreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ragicorp.lydiacontact.contactListScreen.views.ContactItem
import org.koin.androidx.compose.koinViewModel

internal object ContactList {
    const val Route = "contactList"

    @Composable
    fun Screen(
        contactListViewModel: ContactListViewModel = koinViewModel(),
        navigateToContactDetail: (String) -> Unit
    ) {
        val contacts =
            contactListViewModel.contacts.collectAsStateWithLifecycle(initialValue = emptyList())
        LaunchedEffect(key1 = null) {
            contactListViewModel.fetchContacts()
        }
        Scaffold { padding ->
            Surface(modifier = Modifier.padding(padding)) {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(contacts.value) {
                        ContactItem(contact = it)
                    }
                }
            }
        }
    }
}