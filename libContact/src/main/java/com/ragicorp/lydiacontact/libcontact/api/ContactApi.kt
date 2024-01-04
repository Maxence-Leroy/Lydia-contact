package com.ragicorp.lydiacontact.libcontact.api

import com.google.gson.annotations.JsonAdapter

data class NameApi(
    val title: String,
    val first: String,
    val last: String
)

data class StreetApi(
    val number: Int,
    val name: String
)

data class LocationApi(
    val street: StreetApi,
    val city: String,
    val state: String,
    val country: String,
    val postcode: String
)

data class DobApi(
    val date: String
)

data class PictureApi(
    val large: String,
    val thumbnail: String
)

data class ContactApi(
    val name: NameApi,
    val location: LocationApi,
    val dob: DobApi,
    val nat: String,
    val cell: String,
    val email: String,
    val picture: PictureApi
)

data class ContactApiResponse(
    val results: List<ContactApi>
)