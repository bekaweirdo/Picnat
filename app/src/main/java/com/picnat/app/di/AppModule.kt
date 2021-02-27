package com.picnat.app.di

import androidx.room.Room
import com.picnat.app.PicnatAppNavigator
import com.picnat.app.data.database.PicnatDatabase
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import com.google.firebase.firestore.FirebaseFirestore
import com.picnat.core.data.repository.user_repository.UserRepositoryImpl
import com.picnat.core.data.resource_provider.ResourceProvider
import com.picnat.core.navigation.impl.GlobalNavigatorImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val appModule = module {
    single { ResourceProvider(get()) }
    single { UserRepositoryImpl(database = FirebaseFirestore.getInstance()) }
}

val roomDataSourceModule = module {
    single {
        Room.databaseBuilder(androidApplication(), PicnatDatabase::class.java, "Picnat")
            .build()
    }
    //    single { get<PicnatDatabase>().picnatDAO }
}

val navigationModule = module {
    single { Cicerone.create(customRouter = Router()) }
    single { get<Cicerone<Router>>().getNavigatorHolder() }
    single { get<Cicerone<Router>>().router }
    single { PicnatAppNavigator(activity = get(), containerRes = get()) }
    single { GlobalNavigatorImpl(router = get(), get()) }
}