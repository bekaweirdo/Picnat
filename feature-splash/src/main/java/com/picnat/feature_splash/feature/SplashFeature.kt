package com.picnat.feature_splash.feature

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.picnat.core.base.BaseFeature
import com.picnat.core.data.repository.user_repository.UserRepositoryImpl
import com.picnat.feature_splash.ui.SplashFragment
import com.picnat.feature_splash.ui.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.unloadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object SplashFeature : BaseFeature {

    private val splashModule = module {
        single { FirebaseFirestore.getInstance() }
        single { FirebaseAuth.getInstance() }
        viewModel { SplashViewModel(get(), get()) }
        single { UserRepositoryImpl(get()) }
    }

    override val modulesList: List<Module>
        get() = listOf(splashModule)

    override fun unloadModules() {
        unloadKoinModules(modulesList)
    }

    object SplashScreens {
        fun splashScreen() = FragmentScreen { SplashFragment() }
    }

}