package com.example.picnat.koin

import androidx.room.Room
import com.example.picnat.MainActivity
import com.example.picnat.PicnatAppNavigator
import com.example.picnat.R
import com.example.picnat.data.database.PicnatDatabase
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val roomDataSourceModule = module {
    single {
        Room.databaseBuilder(androidApplication(), PicnatDatabase::class.java, "Picnat")
            .build()
    }
}

val navigationModule = module {
    scope<MainActivity> {
        scoped { PicnatAppNavigator(get()) }
    }
    single { Cicerone.create(Router()) }
    single { get<Cicerone<Router>>().getNavigatorHolder() }
    single { get<Cicerone<Router>>().router }
}