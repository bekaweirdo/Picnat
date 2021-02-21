package com.picnat.feature_auth.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picnat.app.network.extension.go
import com.picnat.feature_auth.network.repository.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class LogInViewModel @Inject constructor(private val authRepository: AuthRepository) : ViewModel() {
    fun login(email : String, password : String) {
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