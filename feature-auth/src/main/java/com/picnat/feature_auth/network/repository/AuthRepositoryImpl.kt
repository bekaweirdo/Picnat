package com.picnat.feature_auth.network.repository

import com.picnat.feature_auth.network.data.AuthDataSource

class AuthRepositoryImpl(private val authDataSource: AuthDataSource) : AuthRepository {

    override suspend fun login(email: String, password: String) = authDataSource.login(email, password)

    override suspend fun register(email: String, password: String) = authDataSource.signUp(email, password)
}