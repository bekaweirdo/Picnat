package com.picnat.feature_auth.network.repository

import com.picnat.feature_auth.network.data.AuthDataSource
import javax.inject.Inject

class AuthRepository @Inject constructor(private val auth: AuthDataSource) {

    suspend fun login(email: String, password: String) = auth.login(email, password)

    suspend fun register(email: String, password: String) = auth.register(email, password)
}