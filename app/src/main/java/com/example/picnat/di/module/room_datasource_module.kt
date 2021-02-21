package com.example.picnat.di.module

import com.example.picnat.data.database.PicnatDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val roomDataSourceModule = module {
    single {
        Room.databaseBuilder(androidApplication(), PicnatDatabase::class.java, "Picnat")
            .build()
    }

    // Expose picnat DAO
    single { get<PicnatDatabase>().bookmarkDao() }
}