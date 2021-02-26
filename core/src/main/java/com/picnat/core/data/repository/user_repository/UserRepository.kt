package com.picnat.core.data.repository.user_repository

import com.picnat.core.data.models.User
import com.picnat.core.network.ResponseState

interface UserRepository {

    suspend fun getData(userId : String) : ResponseState<User>

    suspend fun saveData(user : User): ResponseState<Nothing>

    suspend fun writeData(userId: String, field : String, value : Any) : ResponseState<Nothing>
}

