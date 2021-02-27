package com.picnat.app.di

import android.content.Context
import androidx.room.Room
import com.picnat.app.PicnatAppNavigator
import com.picnat.app.data.database.PicnatDatabase
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import com.google.firebase.firestore.FirebaseFirestore
import com.picnat.core.base.coroutines.CoroutineDispatcherProvider
import com.picnat.core.data.repository.user_repository.UserRepository
import com.picnat.core.data.repository.user_repository.UserRepositoryImpl
import com.picnat.core.data.resource_provider.ResourceProvider
import com.picnat.core.navigation.api.GlobalNavigator
import com.picnat.core.navigation.impl.GlobalNavigatorImpl
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val appModule = module {
    single { ResourceProvider(get()) }

    fun provideUseRepository(database: FirebaseFirestore) : UserRepository {
        return UserRepositoryImpl(database)
    }
    single { provideUseRepository(database = FirebaseFirestore.getInstance()) }
}

val roomDataSourceModule = module {
    single {
        Room.databaseBuilder(androidApplication(), PicnatDatabase::class.java, "Picnat")
            .build()
    }
    //    single { get<PicnatDatabase>().picnatDAO }
}

val coroutineDispatcherProviderModule = module {
    single { CoroutineDispatcherProvider(Dispatchers.Main, Dispatchers.Default, Dispatchers.IO) }
}
val navigationModule = module {
    single { Cicerone.create(customRouter = Router()) }
    single { get<Cicerone<Router>>().getNavigatorHolder() }
    single { get<Cicerone<Router>>().router }
    single { PicnatAppNavigator(activity = get(), containerRes = get()) }
    fun provideGlobalNavigator(router: Router, context: Context) : GlobalNavigator {
        return GlobalNavigatorImpl(router, context)
    }
    single { provideGlobalNavigator(router = get(), get()) }
}