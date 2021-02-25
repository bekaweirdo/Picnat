package com.picnat.feature_auth.ui.sign_up_info

import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.picnat.core.App
import com.picnat.core.base.BaseFeatureVM
import com.picnat.core.data.repository.user_repository.UserRepositoryImpl
import com.picnat.core.network.extension.go
import com.picnat.feature_auth.R
import com.picnat.feature_auth.data.repository.AuthRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignUpInfoViewModel(
    private val authRepository: AuthRepositoryImpl,
    private val userRepository: UserRepositoryImpl
) : BaseFeatureVM() {

    fun signUp(email: String, password: String, confirmPassword: String) {

        viewModelScope.launch(Dispatchers.IO) {
            authRepository.signUp(email, password).go(
                success = { getUserData(it!!) },
                failure = { errorMessage.postValue(resourceProvider.getString(R.string.auth_could_not_sign_up)) }
            )
        }
    }

    private fun getUserData(user: FirebaseUser) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.getUser(user.uid).go(
                success = {
                    App.currentUser = it
                    //globalNavigator.loadOnboarding()
                },
                failure = { errorMessage.postValue(resourceProvider.getString(R.string.auth_could_not_get_user_data)) }
            )
        }
    }
}