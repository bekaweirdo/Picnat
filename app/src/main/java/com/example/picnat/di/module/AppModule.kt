package com.example.picnat.di.module

import com.example.picnat.data.datasource.FirebaseDataSource
import com.example.picnat.repository.UserRepository
import com.example.picnat.ui.auth.AuthViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

//class AppModule {
//
//    @Provides
//    @Singleton
//    fun provideAuthViewModelFactory(
//        userRepository: UserRepository
//    ): AuthViewModelFactory = AuthViewModelFactory(userRepository)
//
//    @Provides
//    @Singleton
//    fun provideUserRepository(
//        firebaseDataSource: FirebaseDataSource
//    ): UserRepository = UserRepository(firebaseDataSource)
//
//    @Provides
//    @Singleton
//    fun provideFirebaseDataSource(): FirebaseDataSource = FirebaseDataSource()
//}

val appModule = module {

    viewModel { AuthViewModel(get()) }

    single(createdAtStart = true) { UserRepository(get()) }

    single(createdAtStart = true) { FirebaseDataSource() }
}

val roomPicnatApp = listOf(roomDataSourceModule)