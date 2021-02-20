package com.example.picnat.data.datasource

import androidx.lifecycle.MutableLiveData
import com.example.picnat.data.ResponseState
import com.example.picnat.data.ResponseState.Companion.failed
import com.example.picnat.data.ResponseState.Companion.success
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await

class FirebaseDataSource {

    val currentUser: MutableLiveData<FirebaseUser> = MutableLiveData()

    private val firebaseAuth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    suspend fun login(email: String, password: String): ResponseState<FirebaseUser?> {
        return try {
            val data = firebaseAuth
                .signInWithEmailAndPassword(email, password)
                .await()
            success(data.user)
        } catch (e: Exception) {
            failed(e.message.toString())
        }
    }

    suspend fun register(email: String, password: String): ResponseState<FirebaseUser?> {
        return try {
            val data = firebaseAuth
                .createUserWithEmailAndPassword(email, password)
                .await()
            success(data.user)
        } catch (e: Exception) {
            failed(e.message.toString())
        }
    }

    fun logout() = firebaseAuth.signOut()

    fun currentUser() = currentUser.postValue(firebaseAuth.currentUser)

}