package com.picnat.app.koin

import androidx.room.Room
import com.picnat.app.PicnatAppNavigator
import com.picnat.app.data.database.PicnatDatabase
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val roomDataSourceModule = module {
    single {
        Room.databaseBuilder(androidApplication(), PicnatDatabase::class.java, "Picnat")
            .build()
    }
}

val navigationModule = module {
    single { PicnatAppNavigator(activity = get()) }
    single { Cicerone.create(customRouter = Router()) }
    single { get<Cicerone<Router>>().getNavigatorHolder() }
    single { get<Cicerone<Router>>().router }
}