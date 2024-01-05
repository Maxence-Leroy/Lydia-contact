package com.ragicorp.lydiacontact.contactListScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ragicorp.lydiacontact.libcontact.ContactRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

enum class FetchingState {
    LOADING,
    ERROR,
    SUCCESS
}

class ContactListViewModel(
    private val contactRepository: ContactRepository
) : ViewModel() {
    val contacts =
        contactRepository.getContacts().stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
    private val _fetchingState = MutableStateFlow(FetchingState.SUCCESS)
    val fetchingState = _fetchingState.asStateFlow()

    fun fetchMoreContacts() {
        viewModelScope.launch {
            _fetchingState.emit(FetchingState.LOADING)
            val nextPage = (contacts.value.size / ContactRepository.contactsPerPage) + 1
            try {
                contactRepository.fetchContacts(page = nextPage)
                _fetchingState.emit(FetchingState.SUCCESS)
            } catch (e: Throwable) {
                _fetchingState.emit(FetchingState.ERROR)
            }
        }
    }
}