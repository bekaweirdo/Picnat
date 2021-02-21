package com.example.picnat.koin

import androidx.room.Room
import com.example.picnat.data.database.PicnatDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val appModule = module {
    module { roomDataSourceModule }
}

val roomDataSourceModule = module {
    single {
        Room.databaseBuilder(androidApplication(), PicnatDatabase::class.java, "Picnat")
            .build()
    }
}