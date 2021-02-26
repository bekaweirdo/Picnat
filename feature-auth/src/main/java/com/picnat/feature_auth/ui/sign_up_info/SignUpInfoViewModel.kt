package com.picnat.feature_auth.ui.sign_up_info

import androidx.lifecycle.viewModelScope
import com.picnat.core.App
import com.picnat.core.base.BaseFeatureVM
import com.picnat.core.data.models.User
import com.picnat.core.data.repository.user_repository.UserRepositoryImpl
import com.picnat.core.network.extension.go
import com.picnat.feature_auth.R
import com.picnat.feature_auth.data.repository.AuthRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignUpInfoViewModel(
    private val authRepository: AuthRepositoryImpl,
    private val userRepository: UserRepositoryImpl
) : BaseFeatureVM() {

    fun signUp(
        email: String,
        password: String,
        username: String,
        firstName: String,
        lastName: String
    ) {
        if (username.isBlank() || firstName.isBlank() || lastName.isBlank())
            errorMessage.postValue(resourceProvider.getString(R.string.fill_all_the_fields))

        viewModelScope.launch(Dispatchers.IO) {
            authRepository.signUp(email, password).go(
                onSuccessWithData = {
                    val user = User(
                        userId = it!!.uid,
                        username = username,
                        userEmail = it.email!!,
                        userFirstName = firstName,
                        userLastName = lastName,
                        userProfilePicture = null
                    )
                    writeUserData(user)
                },
                onFailure = { errorMessage.postValue(it) }
            )
        }
    }

    private fun writeUserData(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.saveData(user).go(
                onSuccessWithData = {
                    App.currentUser = user
                    //globalNavigator.loadOnboarding()
                },
                onFailure = { errorMessage.postValue(resourceProvider.getString(R.string.auth_could_not_get_user_data)) }
            )
        }
    }
}