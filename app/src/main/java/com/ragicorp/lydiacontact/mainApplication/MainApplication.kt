package com.ragicorp.lydiacontact.mainApplication

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.disk.DiskCache
import coil.memory.MemoryCache
import com.ragicorp.lydiacontact.contactDetailScreen.ContactDetailViewModel
import com.ragicorp.lydiacontact.contactListScreen.ContactListViewModel
import com.ragicorp.lydiacontact.libcontact.contactModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import java.util.UUID

class MainApplication : Application(), ImageLoaderFactory {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(
                contactModule,
                module {
                    viewModel { ContactListViewModel(get()) }
                    viewModel { (contactId: UUID) ->
                        ContactDetailViewModel(contactId, get())
                    }
                }
            )
        }
    }

    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            .memoryCache {
                MemoryCache.Builder(this)
                    .maxSizePercent(1.0)
                    .build()
            }
            .diskCache {
                DiskCache.Builder()
                    .directory(this.cacheDir.resolve("image_cache"))
                    .maxSizePercent(1.0)
                    .build()
            }
            .build()
    }
}