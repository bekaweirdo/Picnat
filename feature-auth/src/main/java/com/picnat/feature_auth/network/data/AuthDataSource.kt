package com.picnat.feature_auth.network.data

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.picnat.core.network.ResponseState
import com.picnat.core.network.ResponseState.Companion.failed
import com.picnat.core.network.ResponseState.Companion.success
import kotlinx.coroutines.tasks.await

class AuthDataSource {

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
}