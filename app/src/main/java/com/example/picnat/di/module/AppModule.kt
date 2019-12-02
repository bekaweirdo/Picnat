package com.example.picnat.di.module

import com.example.picnat.data.datasource.FirebaseDataSource
import com.example.picnat.repository.UserRepository
import com.example.picnat.ui.auth.AuthViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideAuthViewModelFactory(
        userRepository: UserRepository
    ): AuthViewModelFactory = AuthViewModelFactory(userRepository)

    @Provides
    @Singleton
    fun provideUserRepository(
        firebaseDataSource: FirebaseDataSource
    ): UserRepository = UserRepository(firebaseDataSource)

    @Provides
    @Singleton
    fun provideFirebaseDataSource(): FirebaseDataSource = FirebaseDataSource()
}