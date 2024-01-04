package com.ragicorp.lydiacontact.contactListScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ragicorp.lydiacontact.libcontact.ContactDomain
import com.ragicorp.lydiacontact.libcontact.ContactRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ContactListViewModel(
    private val contactRepository: ContactRepository
) : ViewModel() {
    private val _contacts = MutableStateFlow(emptyList<ContactDomain>())
    val contacts = _contacts.asStateFlow()

    fun fetchContacts() {
        viewModelScope.launch {
            _contacts.emit(contactRepository.fetchContacts())
        }
    }
}