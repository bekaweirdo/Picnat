package com.example.picnat.repository


import android.util.Log
import androidx.lifecycle.LiveData
import com.example.picnat.data.datasource.FirebaseDataSource
import com.google.firebase.auth.FirebaseUser

class UserRepository(private val firebase: FirebaseDataSource) {

    suspend fun login(email: String, password: String) = firebase.login(email, password)

    suspend fun register(email: String, password: String) = firebase.register(email, password)

    fun currentUser(): LiveData<FirebaseUser> {
        firebase.currentUser()
        return firebase.currentUser
    }

    fun logout() = firebase.logout()
}