package com.picnat.core.data.repository.user_repository

import com.picnat.core.data.models.User
import com.picnat.core.network.ResponseState
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun getData(userId : String) : Flow<ResponseState<User>>

    suspend fun saveData(user : User): Flow<ResponseState<Nothing>>

    suspend fun writeData(userId: String, field : String, value : Any) : Flow<ResponseState<Nothing>>
}

