package com.picnat.core.data.repository.user_repository

import com.picnat.core.data.models.User
import com.picnat.core.network.ResponseState

interface UserRepository {

    suspend fun getUser(userId : String) : ResponseState<User>

}