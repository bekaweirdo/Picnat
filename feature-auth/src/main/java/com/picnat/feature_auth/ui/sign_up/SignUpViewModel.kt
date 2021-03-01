package com.picnat.feature_auth.ui.sign_up

import androidx.core.os.bundleOf
import androidx.lifecycle.MutableLiveData
import com.picnat.feature_auth.R
import com.picnat.feature_auth.data.model.SignUpInfoModel
import com.picnat.feature_auth.feature.AuthFeature
import com.picnat.feature_auth.feature.ui.AuthFeatureVM
import com.picnat.feature_auth.ui.sign_up_info.SignUpInfoFragment

class SignUpViewModel : AuthFeatureVM() {

    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val confirmPassword = MutableLiveData<String>()

    fun signUp() {
        if (checkFields(
                listOf(
                    email.value!!,
                    password.value!!,
                    confirmPassword.value!!
                )
            ) && checkEmailValidity(email.value!!) && checkPasswordValidity(password.value!!)
        ) {
            if (password.value!! != confirmPassword.value!!) {
                errorMessage.postValue(resourceProvider.getString(R.string.passwords_doesnt_match))
                return
            }

            val signUpInfoData = bundleOf(
                SignUpInfoFragment.SIGN_UP_INFO_MODEL to SignUpInfoModel(
                    email.value!!,
                    password.value!!
                )
            )

            localNavigator.navigateTo(AuthFeature.AuthScreens.authSignUpInfo(signUpInfoData))
        }
    }
}
