package com.picnat.feature_auth.domain

import com.picnat.core.base.coroutines.CoroutineDispatcherProvider
import com.picnat.core.base.use_case.FlowInteractor
import com.picnat.core.data.models.User
import com.picnat.core.data.repository.user_repository.UserRepository
import com.picnat.core.network.ResponseState
import kotlinx.coroutines.flow.Flow

class SaveUserUseCase(
    private val userRepository: UserRepository,
    dispatcherProvider: CoroutineDispatcherProvider
) : FlowInteractor<SaveUserUseCase.Params, Nothing>(dispatcherProvider) {

    data class Params(val user: User)

    override suspend fun execute(params: Params?): Flow<ResponseState<Nothing>> {
        return userRepository.saveData(params!!.user)
    }
}