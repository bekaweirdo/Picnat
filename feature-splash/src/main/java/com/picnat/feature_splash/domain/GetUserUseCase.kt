package com.picnat.feature_splash.domain

import com.picnat.core.base.coroutines.CoroutineDispatcherProvider
import com.picnat.core.base.use_case.FlowInteractor
import com.picnat.core.data.models.User
import com.picnat.core.data.repository.user_repository.UserRepository
import com.picnat.core.network.ResponseState
import kotlinx.coroutines.flow.Flow

class GetUserUseCase(
    private val userRepository: UserRepository,
    dispatcherProvider: CoroutineDispatcherProvider
) : FlowInteractor<GetUserUseCase.Params, User>(dispatcherProvider) {

    data class Params(val userId: String)

    override suspend fun execute(params: Params?): Flow<ResponseState<User>> {
        return userRepository.getData(params!!.userId)
    }
}