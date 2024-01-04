package com.ragicorp.lydiacontact.libcontact

import com.ragicorp.lydiacontact.libcontact.api.ContactApiService
import com.ragicorp.lydiacontact.libcontact.api.ContactDomainApiConverter

class ContactRepository(
    private val contactApiService: ContactApiService
) {
    suspend fun fetchContacts(): List<ContactDomain> {
        val response = contactApiService.fetchContacts(0)
        return ContactDomainApiConverter.contactFromApi(response)
    }
}