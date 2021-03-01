package com.picnat.feature_auth.domain

import com.google.firebase.auth.FirebaseUser
import com.picnat.core.base.coroutines.CoroutineDispatcherProvider
import com.picnat.core.base.use_case.FlowInteractor
import com.picnat.core.network.ResponseState
import com.picnat.feature_auth.data.data_source.AuthDataSource
import kotlinx.coroutines.flow.Flow

class SignUpUseCase(
    private val authDataSource: AuthDataSource,
    dispatcherProvider: CoroutineDispatcherProvider
) : FlowInteractor<SignUpUseCase.Params, FirebaseUser?>(dispatcherProvider) {

    data class Params(val email: String, val password: String)

    override suspend fun execute(params: Params?): Flow<ResponseState<FirebaseUser?>> {
        return authDataSource.login(params!!.email, params.password)
    }
}