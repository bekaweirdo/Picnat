package com.picnat.feature_auth.feature

import android.os.Bundle
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.google.firebase.auth.FirebaseAuth
import com.picnat.core.base.BaseFeature
import com.picnat.core.navigation.api.LocalNavigator
import com.picnat.core.navigation.impl.LocalNavigatorImpl
import com.picnat.feature_auth.data.data_source.AuthDataSource
import com.picnat.feature_auth.data.repository.AuthRepository
import com.picnat.feature_auth.data.repository.AuthRepositoryImpl
import com.picnat.feature_auth.domain.GetUserUseCase
import com.picnat.feature_auth.domain.LogInUseCase
import com.picnat.feature_auth.domain.SaveUserUseCase
import com.picnat.feature_auth.domain.SignUpUseCase
import com.picnat.feature_auth.ui.login.LogInFragment
import com.picnat.feature_auth.ui.login.LogInViewModel
import com.picnat.feature_auth.ui.sign_up.SignUpFragment
import com.picnat.feature_auth.ui.sign_up.SignUpViewModel
import com.picnat.feature_auth.ui.sign_up_info.SignUpInfoFragment
import com.picnat.feature_auth.ui.sign_up_info.SignUpInfoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object AuthFeature : BaseFeature {

    private val viewModelModule: Module = module {
        viewModel { LogInViewModel(getUserUseCase = get(), logInUseCase = get()) }
        viewModel { SignUpViewModel() }
        viewModel { SignUpInfoViewModel(saveUserUseCase = get(), signUpUseCase = get()) }
    }

    private val useCasesModule = module {
        single { GetUserUseCase(userRepository = get(), dispatcherProvider = get()) }
        single { SaveUserUseCase(userRepository = get(), dispatcherProvider = get()) }
        single { LogInUseCase(authDataSource = get(), dispatcherProvider = get()) }
        single { SignUpUseCase(authDataSource = get(), dispatcherProvider = get()) }
    }

    private val repositoryModule: Module = module {
        fun provideAuthRepository(authDataSource: AuthDataSource) : AuthRepository {
            return AuthRepositoryImpl(authDataSource)
        }
        single { provideAuthRepository(authDataSource = get()) }
    }

    private val dataSourceModule: Module = module {
        single { AuthDataSource(firebaseAuth = FirebaseAuth.getInstance()) }
    }

    private val localNavigatorModule : Module = module {
//        scope<AuthFeatureFragment> {
//            scoped { LocalNavigatorHandlerImpl(get()) }
//        }
        fun provideLocalNavigator(router: Router) : LocalNavigator {
            return LocalNavigatorImpl(router)
        }
        single { provideLocalNavigator(get()) }
    }

//    override val featureFragment: KClass<out BaseFeatureFragment<*>>
//        get() = AuthFeatureFragment::class

    override val modulesList = listOf(
        dataSourceModule,
        repositoryModule,
        useCasesModule,
        viewModelModule,
        localNavigatorModule
    )

    override fun unloadModules() {
        super.unloadModules()
    }

    object AuthScreens {
        fun authLogIn() = FragmentScreen { LogInFragment() }
        fun authSignUp() = FragmentScreen { SignUpFragment() }
        fun authSignUpInfo(data : Bundle) = FragmentScreen { SignUpInfoFragment.newInstance(data) }
    }
}