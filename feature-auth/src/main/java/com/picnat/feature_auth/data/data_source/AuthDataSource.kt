package com.picnat.feature_auth.data.data_source

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.picnat.core.network.ResponseState
import com.picnat.core.network.ResponseState.Companion.failed
import com.picnat.core.network.ResponseState.Companion.loading
import com.picnat.core.network.ResponseState.Companion.successWithData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await


class AuthDataSource(private val firebaseAuth: FirebaseAuth) {

    suspend fun login(email: String, password: String): Flow<ResponseState<FirebaseUser?>> = flow {
        emit(loading())
        try {
            val data = firebaseAuth
                .signInWithEmailAndPassword(email, password)
                .await()
            emit(successWithData(data.user))
        } catch (e: Exception) {
            emit(failed<FirebaseUser?>(e.message.toString()))
        }
    }

    suspend fun signUp(email: String, password: String): Flow<ResponseState<FirebaseUser?>> = flow {
        emit(loading())
         try {
            val data = firebaseAuth
                .createUserWithEmailAndPassword(email, password)
                .await()
            emit(successWithData(data.user))
        } catch (e: Exception) {
            emit(failed<FirebaseUser?>(e.message.toString()))
        }
    }
}