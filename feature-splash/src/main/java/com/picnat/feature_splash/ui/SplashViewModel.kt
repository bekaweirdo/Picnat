package com.picnat.feature_splash.ui

import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.picnat.core.App
import com.picnat.core.base.BaseFeatureVM
import com.picnat.core.data.repository.user_repository.UserRepositoryImpl
import com.picnat.core.network.extension.go
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel(
    private val userRepository: UserRepositoryImpl,
    private val firebaseAuth: FirebaseAuth
) : BaseFeatureVM() {

    init {
        logIn()
    }

    fun logIn() {
        viewModelScope.launch(Dispatchers.IO) {
            val user = firebaseAuth.currentUser
            if (user != null) {
                userRepository.getData(user.uid).go(
                    onSuccessWithData = { data -> App.currentUser = data },
                    onFailure = { globalNavigator.loadAuthFeature() }
                )
            } else {
                delay(1000)
                globalNavigator.loadAuthFeature()
            }
        }
    }

}