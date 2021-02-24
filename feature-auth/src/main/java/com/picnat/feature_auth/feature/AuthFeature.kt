package com.picnat.feature_auth.feature

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.google.firebase.auth.FirebaseAuth
import com.picnat.core.base.BaseFeature
import com.picnat.core.navigation.impl.LocalNavigatorImpl
import com.picnat.feature_auth.network.data.AuthDataSource
import com.picnat.feature_auth.network.repository.AuthRepositoryImpl
import com.picnat.feature_auth.ui.login.LogInFragment
import com.picnat.feature_auth.ui.login.LogInViewModel
import com.picnat.feature_auth.ui.sign_up.SignUpFragment
import com.picnat.feature_auth.ui.sign_up.SignUpViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object AuthFeature : BaseFeature {

    private val viewModelModule: Module = module {
        viewModel { LogInViewModel(authRepository = get()) }
        viewModel { SignUpViewModel(authRepository = get()) }
    }

    private val repositoryModule: Module = module {
        single { AuthRepositoryImpl(authDataSource = get()) }
    }

    private val dataSourceModule: Module = module {
        single { AuthDataSource(firebaseAuth = FirebaseAuth.getInstance()) }
    }

    private val localNavigatorModule : Module = module {
//        scope<AuthFeatureFragment> {
//            scoped { LocalNavigatorHandlerImpl(get()) }
//        }
        single { LocalNavigatorImpl(get()) }
    }

//    override val featureFragment: KClass<out BaseFeatureFragment<*>>
//        get() = AuthFeatureFragment::class

    override val modulesList = listOf(
        dataSourceModule,
        repositoryModule,
        viewModelModule,
        localNavigatorModule
    )

    override fun unloadModules() {
        super.unloadModules()
    }

    object AuthScreens{
        fun authLogIn() = FragmentScreen { LogInFragment() }
        fun authSignUp() = FragmentScreen { SignUpFragment() }
    }
}