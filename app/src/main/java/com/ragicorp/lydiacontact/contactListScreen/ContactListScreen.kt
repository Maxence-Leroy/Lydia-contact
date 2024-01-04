package com.ragicorp.lydiacontact.contactListScreen

import androidx.compose.runtime.Composable
import com.ragicorp.lydiacontact.contactListScreen.views.ContactItem
import com.ragicorp.lydiacontact.libcontact.stubContact

internal object ContactList {
    const val Route = "contactList"

    @Composable
    fun Screen(
        navigateToContactDetail: (String) -> Unit
    ) {
        ContactItem(contact = stubContact)
    }
}