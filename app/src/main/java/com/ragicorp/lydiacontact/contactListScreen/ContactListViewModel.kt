package com.ragicorp.lydiacontact.contactListScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ragicorp.lydiacontact.libcontact.ContactRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ContactListViewModel(
    private val contactRepository: ContactRepository
) : ViewModel() {
    val contacts =
        contactRepository.getContacts().stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun fetchMoreContacts() {
        viewModelScope.launch {
            val nextPage = (contacts.value.size / 20) + 1 // TODO: use variable
            contactRepository.fetchContacts(page = nextPage)
        }
    }
}