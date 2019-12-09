package com.example.picnat.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.picnat.repository.UserRepository
import com.google.firebase.auth.FirebaseUser
import io.reactivex.disposables.CompositeDisposable

class HomeViewModel(private val repository: UserRepository) : ViewModel(){


    val userDetail by lazy {
         repository.currentUser()
    }

}