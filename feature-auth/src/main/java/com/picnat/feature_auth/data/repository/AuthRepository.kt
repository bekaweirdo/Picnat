package com.picnat.feature_auth.data.repository

import com.google.firebase.auth.FirebaseUser
import com.picnat.core.network.ResponseState

interface AuthRepository{

    suspend fun login(email: String, password: String) : ResponseState<FirebaseUser?>

    suspend fun signUp(email: String, password: String) : ResponseState<FirebaseUser?>
}