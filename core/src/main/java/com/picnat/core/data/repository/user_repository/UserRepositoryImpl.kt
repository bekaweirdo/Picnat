package com.picnat.core.data.repository.user_repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.picnat.core.data.models.User
import com.picnat.core.network.ResponseState
import com.picnat.core.network.ResponseState.Companion.failed
import com.picnat.core.network.ResponseState.Companion.loading
import com.picnat.core.network.ResponseState.Companion.success
import com.picnat.core.network.ResponseState.Companion.successWithData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class UserRepositoryImpl(private val database: FirebaseFirestore) : UserRepository {

    companion object {
        private const val USER_COLLECTION = "users"
    }

    override suspend fun getData(userId: String): Flow<ResponseState<User>> = flow {
        emit(loading())
        val userDocument = database.collection(USER_COLLECTION).document(userId).get().await()
        val user = userDocument.toObject(User::class.java)
        if (user != null)
            emit(successWithData(user))
        else
            emit(failed<User>("User doesn't exists"))
    }

    override suspend fun saveData(user: User): Flow<ResponseState<Nothing>> = flow {
        emit(loading())
        database.collection(USER_COLLECTION).document(user.userId).set(user).await()
        emit(success())
    }

    override suspend fun writeData(
        userId: String,
        field: String,
        value: Any
    ): Flow<ResponseState<Nothing>> = flow {
        emit(loading())
        try {
            database.collection(USER_COLLECTION).document(userId).update(field, value).await()
            emit(success<Nothing>())
        } catch (e: FirebaseFirestoreException) {
            emit(failed<Nothing>("Failed updating data!"))
        }
    }

}