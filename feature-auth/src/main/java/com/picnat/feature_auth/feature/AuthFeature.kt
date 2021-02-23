package com.picnat.feature_auth.feature

import com.google.firebase.auth.FirebaseAuth
import com.picnat.feature_auth.network.data.AuthDataSource
import com.picnat.feature_auth.network.repository.AuthRepositoryImpl
import com.picnat.feature_auth.ui.login.LogInViewModel
import com.picnat.feature_auth.ui.sign_up.SignUpViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object AuthFeature {

    private val viewModelModule: Module = module {
        viewModel { LogInViewModel(authRepository = AuthRepositoryImpl(get())) }
        viewModel { SignUpViewModel(authRepository = get()) }
    }

    private val repositoryModule: Module = module {
        single { AuthRepositoryImpl(authDataSource = get()) }
    }

    private val dataSourceModule : Module = module {
        single { AuthDataSource(firebaseAuth = FirebaseAuth.getInstance()) }
    }

    fun init() = loadKoinModules(
        listOf(
            dataSourceModule,
            repositoryModule,
            viewModelModule
        )
    )
}