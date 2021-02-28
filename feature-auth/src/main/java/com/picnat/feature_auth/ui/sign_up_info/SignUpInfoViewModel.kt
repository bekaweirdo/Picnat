package com.picnat.feature_auth.ui.sign_up_info

import androidx.lifecycle.viewModelScope
import com.picnat.core.App
import com.picnat.core.base.BaseFeatureVM
import com.picnat.core.data.models.User
import com.picnat.core.network.extension.go
import com.picnat.feature_auth.R
import com.picnat.feature_auth.domain.SaveUserUseCase
import com.picnat.feature_auth.domain.SignUpUseCase
import com.picnat.feature_auth.feature.ui.AuthFeatureVM
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignUpInfoViewModel(
    private val saveUserUseCase: SaveUserUseCase,
    private val signUpUseCase: SignUpUseCase
) : AuthFeatureVM() {

    fun signUp(
        email: String,
        password: String,
        username: String,
        firstName: String,
        lastName: String
    ) {
        if(checkFields(listOf(username, firstName, lastName))){
            viewModelScope.launch(Dispatchers.IO) {
                signUpUseCase.invoke(SignUpUseCase.Params(email, password)).go(
                    loading = { showLoading() },
                    onFinished = { hideLoading() },
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
    }

    private fun writeUserData(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            saveUserUseCase.invoke(SaveUserUseCase.Params(user)).go(
                loading = { showLoading() },
                onFinished = { hideLoading() },
                onSuccessWithData = {
                    App.currentUser = user
                    //globalNavigator.loadOnboarding()
                },
                onFailure = { errorMessage.postValue(resourceProvider.getString(R.string.auth_could_not_get_user_data)) }
            )
        }
    }
}