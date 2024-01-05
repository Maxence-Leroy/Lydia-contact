package com.ragicorp.lydiacontact.libcontact.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contact")
data class ContactDb(
    @PrimaryKey val id: String,
    val firstName: String,
    val lastName: String,
    val title: String,
    val addressStreetNumber: Int,
    val addressStreetName: String,
    val addressCity: String,
    val addressState: String,
    val addressCountry: String,
    val addressPostCode: String,
    val thumbnail: String,
    val pictureLarge: String,
    val dateOfBirth: String,
    val phone: String,
    val email: String,
    val nat: String
)