package com.picnat.feature_auth.data.data_source

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.picnat.core.network.ResponseState
import kotlinx.coroutines.tasks.await


class AuthDataSource(private val firebaseAuth: FirebaseAuth) {

    suspend fun login(email: String, password: String): ResponseState<FirebaseUser?> {
        return try {
            val data = firebaseAuth
                .signInWithEmailAndPassword(email, password)
                .await()
            ResponseState.successWithData(data.user)
        } catch (e: Exception) {
            ResponseState.failed(e.message.toString())
        }
    }

    suspend fun signUp(email: String, password: String): ResponseState<FirebaseUser?> {
        return try {
            val data = firebaseAuth
                .createUserWithEmailAndPassword(email, password)
                .await()
            ResponseState.successWithData(data.user)
        } catch (e: Exception) {
            ResponseState.failed(e.message.toString())
        }
    }
}