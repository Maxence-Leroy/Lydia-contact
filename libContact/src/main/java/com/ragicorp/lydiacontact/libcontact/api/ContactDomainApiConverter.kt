package com.ragicorp.lydiacontact.libcontact.api

import com.ragicorp.lydiacontact.libcontact.AddressDomain
import com.ragicorp.lydiacontact.libcontact.ContactDomain
import com.ragicorp.lydiacontact.libcontact.PictureDomain
import java.time.Instant
import java.util.UUID

internal object ContactDomainApiConverter {
    internal fun contactFromApi(api: ContactApiResponse): List<ContactDomain> =
        api.results.map {
            ContactDomain(
                id = UUID.randomUUID(),
                firstName = it.name.first,
                lastName = it.name.last,
                title = it.name.title,
                address = AddressDomain(
                    streetNumber = it.location.street.number,
                    streetName = it.location.street.name,
                    city = it.location.city,
                    state = it.location.state,
                    country = it.location.country,
                    postCode = it.location.postcode
                ),
                picture = PictureDomain(
                    thumbnail = it.picture.thumbnail,
                    large = it.picture.large
                ),
                dateOfBirth = Instant.parse(it.dob.date),
                phone = it.cell,
                email = it.email,
                nat = it.nat
            )
        }
}