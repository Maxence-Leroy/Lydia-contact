package com.ragicorp.lydiacontact.libcontact.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {
    @Query("SELECT * FROM contact")
    fun getContacts(): Flow<List<ContactDb>>

    @Query("SELECT * FROM contact WHERE id LIKE :contactId LIMIT 1")
    fun getContactById(contactId: String): Flow<ContactDb>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg posts: ContactDb)
}