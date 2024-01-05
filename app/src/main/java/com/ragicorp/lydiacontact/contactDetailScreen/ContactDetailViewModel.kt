package com.ragicorp.lydiacontact.contactDetailScreen

import androidx.lifecycle.ViewModel
import com.ragicorp.lydiacontact.libcontact.ContactRepository
import java.util.UUID

class ContactDetailViewModel(contactId: UUID, private val contactRepository: ContactRepository) :
    ViewModel()