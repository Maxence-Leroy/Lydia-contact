package com.ragicorp.lydiacontact.libcontact

import androidx.room.Room
import com.ragicorp.lydiacontact.libcontact.api.ContactApiService
import com.ragicorp.lydiacontact.libcontact.db.ContactDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val contactModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://randomuser.me/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ContactApiService::class.java)
    }

    single(createdAtStart = true) {
        val context = androidContext()

        Room
            .databaseBuilder(
                context,
                ContactDatabase::class.java, "contacts"
            )
            .build()
    }

    factory { get<ContactDatabase>().contactDao() }

    factory {
        ContactRepository(get())
    }
}