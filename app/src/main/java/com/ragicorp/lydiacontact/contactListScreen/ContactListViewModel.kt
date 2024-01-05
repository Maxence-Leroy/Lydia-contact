package com.ragicorp.lydiacontact.contactListScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ragicorp.lydiacontact.libcontact.ContactRepository
import kotlinx.coroutines.launch

class ContactListViewModel(
    private val contactRepository: ContactRepository
) : ViewModel() {
    val contacts = contactRepository.getContacts()

    fun fetchContacts() {
        viewModelScope.launch {
            contactRepository.fetchContacts()
        }
    }
}