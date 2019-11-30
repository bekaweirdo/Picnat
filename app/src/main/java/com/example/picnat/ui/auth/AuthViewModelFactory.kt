package com.example.picnat.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.picnat.repository.UserRepository

@Suppress("UNCHECKED_CAST")
class AuthViewModelFactory(private val repository: UserRepository) :
    ViewModelProvider.Factory {

    class StubModel : ViewModel()

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass) {
            AuthViewModel::class.java -> AuthViewModel(repository) as T
            else -> StubModel() as T
        }
    }
}