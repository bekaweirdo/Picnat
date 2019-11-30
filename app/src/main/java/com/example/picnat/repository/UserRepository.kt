package com.example.picnat.repository

import com.example.picnat.data.datasource.FirebaseDataSource

class UserRepository(private val firebase: FirebaseDataSource) {

    fun login(email: String, password: String) = firebase.login(email, password)

    fun register(email: String, password: String) = firebase.register(email, password)

    fun currentUser() = firebase.currentUser()

    fun logout() = firebase.logout()
}