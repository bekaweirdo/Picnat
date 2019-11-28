package com.example.picnat.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.picnat.repository.UserRepository
import javax.inject.Inject

open class AuthViewModelFactory @Inject constructor(private val repository: UserRepository): ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AuthViewModel(repository) as T
    }
}