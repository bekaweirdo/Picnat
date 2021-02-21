package com.picnat.feature_auth.feature.di

import com.picnat.feature_auth.network.repository.AuthRepository
import com.picnat.feature_auth.ui.login.LogInViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AuthFeatureModule {

    @Provides
    @Singleton
    fun provideLoginViewModel(
        authRepository: AuthRepository
    ): LogInViewModel = LogInViewModel(authRepository)
}