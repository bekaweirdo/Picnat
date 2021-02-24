package com.picnat.feature_splash.ui

import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.picnat.core.base.BaseFeatureVM
import com.picnat.core.data.repository.UserRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SplashViewModel(private val userRepository: UserRepositoryImpl, private val firebaseAuth: FirebaseAuth) : BaseFeatureVM() {

    init {
        logIn()
    }

    fun logIn(){
        if(firebaseAuth.currentUser != null){
            viewModelScope.launch(Dispatchers.IO) {
                firebaseAuth.currentUser?.uid?.let { userRepository.getUser(it) }
            }
        }
        else
            viewModelScope.launch {
                globalNavigator.loadAuthFeature()
            }
    }

}