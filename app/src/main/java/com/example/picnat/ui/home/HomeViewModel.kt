package com.example.picnat.ui.home

import androidx.lifecycle.ViewModel
import com.example.picnat.repository.UserRepository

class HomeViewModel(private val repository: UserRepository) : ViewModel(){


    val userDetail by lazy {
         repository.currentUser()
    }

}