package com.picnat.core.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.picnat.core.data.models.User
import com.picnat.core.network.ResponseState
import kotlinx.coroutines.tasks.await

class UserRepositoryImpl(private val database : FirebaseFirestore) : UserRepository{

    companion object{
        private const val USER_COLLECTION = "users"
    }

    override suspend fun getUser(userId: String): ResponseState<User> {
        val userDocument = database.collection(USER_COLLECTION).document(userId).get().await()
        val user = userDocument.toObject(User::class.java)
        return if(user != null)
            ResponseState.success(user)
        else
            ResponseState.failed("User doesn't exists")
    }
}