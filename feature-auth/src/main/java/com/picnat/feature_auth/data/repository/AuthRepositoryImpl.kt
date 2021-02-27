package com.picnat.feature_auth.data.repository

import com.picnat.feature_auth.data.data_source.AuthDataSource

class AuthRepositoryImpl(private val authDataSource: AuthDataSource) : AuthRepository {

    override suspend fun login(email: String, password: String) = authDataSource.login(email, password)

    override suspend fun signUp(email: String, password: String) = authDataSource.signUp(email, password)
}