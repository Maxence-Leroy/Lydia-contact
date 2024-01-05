package com.ragicorp.lydiacontact.libcontact.db

import com.ragicorp.lydiacontact.libcontact.AddressDomain
import com.ragicorp.lydiacontact.libcontact.ContactDomain
import com.ragicorp.lydiacontact.libcontact.PictureDomain
import java.time.Instant
import java.util.UUID

object ContactDomainDbConverter {
    fun contactDb(contact: ContactDomain): ContactDb {
        return ContactDb(
            id = contact.id.toString(),
            firstName = contact.firstName,
            lastName = contact.lastName,
            title = contact.title,
            addressStreetNumber = contact.address.streetNumber,
            addressStreetName = contact.address.streetName,
            addressCity = contact.address.city,
            addressState = contact.address.state,
            addressCountry = contact.address.country,
            addressPostCode = contact.address.postCode,
            thumbnail = contact.picture.thumbnail,
            pictureLarge = contact.picture.large,
            dateOfBirth = contact.dateOfBirth.toString(),
            phone = contact.phone,
            email = contact.email,
            nat = contact.nat
        )
    }

    fun contactFromDb(contactDb: ContactDb): ContactDomain {
        return ContactDomain(
            id = UUID.fromString(contactDb.id),
            firstName = contactDb.firstName,
            lastName = contactDb.lastName,
            title = contactDb.title,
            address = AddressDomain(
                streetNumber = contactDb.addressStreetNumber,
                streetName = contactDb.addressStreetName,
                city = contactDb.addressCity,
                state = contactDb.addressState,
                country = contactDb.addressCountry,
                postCode = contactDb.addressPostCode
            ),
            picture = PictureDomain(
                thumbnail = contactDb.thumbnail,
                large = contactDb.pictureLarge
            ),
            dateOfBirth = Instant.parse(contactDb.dateOfBirth),
            phone = contactDb.phone,
            email = contactDb.email,
            nat = contactDb.nat
        )
    }
}