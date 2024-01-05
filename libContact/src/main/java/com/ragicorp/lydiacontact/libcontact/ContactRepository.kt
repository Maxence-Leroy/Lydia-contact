package com.ragicorp.lydiacontact.libcontact

import com.ragicorp.lydiacontact.libcontact.api.ContactApiService
import com.ragicorp.lydiacontact.libcontact.api.ContactDomainApiConverter
import com.ragicorp.lydiacontact.libcontact.db.ContactDao
import com.ragicorp.lydiacontact.libcontact.db.ContactDomainDbConverter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ContactRepository(
    private val contactApiService: ContactApiService,
    private val contactDao: ContactDao
) {

    fun getContacts(): Flow<List<ContactDomain>> {
        return contactDao
            .getContacts()
            .map { contactsDb -> contactsDb.map { ContactDomainDbConverter.contactFromDb(it) } }
    }

    suspend fun fetchContacts(page: Int) {
        val response = contactApiService.fetchContacts(page = page, results = contactsPerPage)
        val contacts = ContactDomainApiConverter.contactFromApi(response)
        val contactsDb = contacts.map { ContactDomainDbConverter.contactDb(it) }
        contactDao.insertAll(*contactsDb.toTypedArray())
    }

    companion object {
        const val contactsPerPage = 20
    }
}