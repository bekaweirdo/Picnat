package com.picnat.feature_auth.ui.login

import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.picnat.core.App
import com.picnat.core.base.BaseFeatureVM
import com.picnat.core.network.extension.go
import com.picnat.feature_auth.R
import com.picnat.feature_auth.domain.GetUserUseCase
import com.picnat.feature_auth.domain.LogInUseCase
import com.picnat.feature_auth.feature.ui.AuthFeatureVM
import com.picnat.feature_auth.utils.ext.isEmailValid
import kotlinx.coroutines.launch

class LogInViewModel(
    private val getUserUseCase: GetUserUseCase,
    private val logInUseCase: LogInUseCase
) : AuthFeatureVM() {

    fun login(email: String, password: String) {
        if(checkFields(listOf(email, password)) && checkEmailValidity(email) && checkPasswordValidity(password)){
            viewModelScope.launch {
                logInUseCase.invoke(LogInUseCase.Params(email, password)).go(
                    loading = { showLoading() },
                    onFinished = { hideLoading() },
                    onSuccessWithData = { getUserData(it!!) },
                    onFailure = { errorMessage.postValue(resourceProvider.getString(R.string.coud_not_get_user_data)) }
                )
            }
        }
    }

    private fun getUserData(user: FirebaseUser) {
        viewModelScope.launch {
            getUserUseCase.invoke(GetUserUseCase.Params(user.uid)).go(
                loading = { showLoading() },
                onFinished = { hideLoading() },
                onSuccessWithData = { App.currentUser = it },
                onFailure = { errorMessage.postValue(resourceProvider.getString(R.string.coud_not_get_user_data)) }
            )
        }
    }
}