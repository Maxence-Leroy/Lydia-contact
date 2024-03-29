package com.ragicorp.lydiacontact.libcontact

import com.google.i18n.addressinput.common.AddressData
import com.google.i18n.addressinput.common.FormOptions
import com.google.i18n.addressinput.common.FormatInterpreter
import com.ragicorp.lydiacontact.libcontact.utils.nationalCodeToEmoji
import java.time.Instant
import java.util.Calendar
import java.util.UUID

data class AddressDomain(
    val streetNumber: Int,
    val streetName: String,
    val city: String,
    val state: String,
    val country: String,
    val postCode: String
)

data class PictureDomain(
    val thumbnail: String,
    val large: String
)

data class ContactDomain(
    val id: UUID,
    val firstName: String,
    val lastName: String,
    val title: String,
    val address: AddressDomain,
    val picture: PictureDomain,
    val dateOfBirth: Instant,
    val phone: String,
    val email: String,
    val nat: String
) {
    val textToShow: String
        get() = "${nationalCodeToEmoji(nat)} $title $firstName $lastName"

    fun generateReadableAddress(): String {
        val addressString = "${address.streetNumber} ${address.streetName}"

        val addressData = AddressData.Builder()
            .setAddress(addressString)
            .setLocality(address.city)
            .setPostalCode(address.postCode)
            .setAdminArea(address.state)
            .setCountry(nat)
            .build()

        val formatInterpreter = FormatInterpreter(FormOptions().createSnapshot())

        val addressFragments = formatInterpreter.getEnvelopeAddress(addressData)

        return addressFragments.joinToString("\n")
    }
}

val stubDate: Instant
    get() {
        val calendar = Calendar.getInstance()
        calendar.set(1980, Calendar.JULY, 31, 2, 0)
        return calendar.time.toInstant()
    }

val stubContact = ContactDomain(
    id = UUID.randomUUID(),
    firstName = "Harry",
    lastName = "Potter",
    title = "Mr",
    address = AddressDomain(
        streetNumber = 4,
        streetName = "Private Drive",
        city = "Little Whinging",
        state = "Surrey",
        country = "UK",
        postCode = "KT23 3AS"
    ),
    picture = PictureDomain(
        thumbnail = "https://upload.wikimedia.org/wikipedia/commons/thumb/9/9b/HP_-_Harry_Potter_wordmark.svg/219px-HP_-_Harry_Potter_wordmark.svg.png",
        large = "https://upload.wikimedia.org/wikipedia/commons/thumb/9/9b/HP_-_Harry_Potter_wordmark.svg/1871px-HP_-_Harry_Potter_wordmark.svg.png"
    ),
    dateOfBirth = stubDate,
    phone = "Ask Hedwige",
    email = "harry.potter@hogwarts.edu",
    nat = "GB"
)