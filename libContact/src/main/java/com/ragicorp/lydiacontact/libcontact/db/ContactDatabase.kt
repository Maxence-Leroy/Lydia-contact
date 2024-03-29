package com.ragicorp.lydiacontact.libcontact.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ContactDb::class], version = 1, exportSchema = false)
internal abstract class ContactDatabase : RoomDatabase() {
    internal abstract fun contactDao(): ContactDao
}

