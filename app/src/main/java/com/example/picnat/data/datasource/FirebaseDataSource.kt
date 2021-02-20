package com.example.picnat.data.datasource

import androidx.lifecycle.MutableLiveData
import com.example.picnat.data.ResponseState
import com.example.picnat.data.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.coroutineScope

class FirebaseDataSource {

    val currentUser: MutableLiveData<FirebaseUser> = MutableLiveData()

    private val firebaseAuth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    fun login(email: String, password: String): ResponseState<FirebaseUser?> {
        val request = firebaseAuth.signInWithEmailAndPassword(email, password)
        return if (request.isSuccessful)
            ResponseState.success(request.result?.user)
        else ResponseState.failed(request.exception.toString())
    }

    fun register(email: String, password: String): ResponseState<FirebaseUser?> {
        val request = firebaseAuth.createUserWithEmailAndPassword(email, password)
        return if (request.isSuccessful)
            ResponseState.success(request.result?.user)
        else ResponseState.failed(request.exception.toString())
    }

    fun logout() = firebaseAuth.signOut()

    fun currentUser() = currentUser.postValue(firebaseAuth.currentUser)

}