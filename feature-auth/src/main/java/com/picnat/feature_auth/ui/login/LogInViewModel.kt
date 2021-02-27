package com.picnat.feature_auth.ui.login

import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.picnat.core.App
import com.picnat.core.base.BaseFeatureVM
import com.picnat.core.data.repository.user_repository.UserRepositoryImpl
import com.picnat.core.locale.LocaleManager
import com.picnat.core.network.extension.go
import com.picnat.feature_auth.R
import com.picnat.feature_auth.data.repository.AuthRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.inject

class LogInViewModel(
    private val authRepository: AuthRepositoryImpl,
    private val userRepository: UserRepositoryImpl
) : BaseFeatureVM() {

    val localManager: LocaleManager by inject()

    fun login(email: String, password: String) {

        if (email.isBlank() || password.isBlank()) {
            errorMessage.postValue(resourceProvider.getString(R.string.fill_all_the_fields))
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            authRepository.login(email, password).go(
                loading = { showLoading() },
                onFinished = { hideLoading() },
                onSuccessWithData = { getUserData(it!!) },
                onFailure = { errorMessage.postValue(resourceProvider.getString(R.string.coud_not_get_user_data)) }
            )
        }
    }

    private fun getUserData(user: FirebaseUser) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.getData(user.uid).go(
                loading = { showLoading() },
                onFinished = { hideLoading() },
                onSuccessWithData = { App.currentUser = it },
                onFailure = { errorMessage.postValue(resourceProvider.getString(R.string.coud_not_get_user_data)) }
            )
        }
    }
}