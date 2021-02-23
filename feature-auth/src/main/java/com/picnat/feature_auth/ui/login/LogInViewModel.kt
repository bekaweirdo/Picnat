package com.picnat.feature_auth.ui.login

import androidx.lifecycle.viewModelScope
import com.picnat.core.base.BaseFeatureVM
import com.picnat.core.network.extension.go
import com.picnat.feature_auth.feature.AuthFeature
import com.picnat.feature_auth.network.repository.AuthRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinApiExtension

@KoinApiExtension
class LogInViewModel (private val authRepository: AuthRepositoryImpl) : BaseFeatureVM() {
    fun login(email : String, password : String) {
        localNavigator.replaceScreen(AuthFeature.AuthScreens.authSignUp())
        viewModelScope.launch(Dispatchers.IO) {
            authRepository.login(email, password).go(
                success = {
                    print(it)
                },
                failure = {
                    print(it)
                }
            )
        }
    }
}