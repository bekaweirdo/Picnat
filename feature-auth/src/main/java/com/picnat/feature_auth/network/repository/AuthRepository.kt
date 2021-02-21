package com.picnat.feature_auth.network.repository

import com.google.firebase.auth.FirebaseUser
import com.picnat.core.network.ResponseState

interface AuthRepository{

    suspend fun login(email: String, password: String) : ResponseState<FirebaseUser?>

    suspend fun register(email: String, password: String) : ResponseState<FirebaseUser?>
}