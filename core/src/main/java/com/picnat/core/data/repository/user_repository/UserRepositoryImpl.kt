package com.picnat.core.data.repository.user_repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.picnat.core.data.models.User
import com.picnat.core.network.ResponseState
import kotlinx.coroutines.tasks.await

class UserRepositoryImpl(private val database : FirebaseFirestore) : UserRepository {

    companion object{
        private const val USER_COLLECTION = "users"
    }

    override suspend fun getData(userId: String): ResponseState<User> {
        val userDocument = database.collection(USER_COLLECTION).document(userId).get().await()
        val user = userDocument.toObject(User::class.java)
        return if(user != null)
            ResponseState.successWithData(user)
        else
            ResponseState.failed("User doesn't exists")
    }

    override suspend fun saveData(user : User) : ResponseState<Nothing> {
        database.collection(USER_COLLECTION).document(user.userId).set(user).await()
        return ResponseState.success()
    }

    override suspend fun writeData(userId: String, field: String, value: Any) : ResponseState<Nothing> {
        return try {
            database.collection(USER_COLLECTION).document(userId).update(field, value).await()
            ResponseState.success()
        }
        catch (e: FirebaseFirestoreException){
            ResponseState.failed("Failed updating data!")
        }
    }

}