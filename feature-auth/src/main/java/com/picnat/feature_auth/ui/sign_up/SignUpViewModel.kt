package com.picnat.feature_auth.ui.sign_up

import androidx.core.os.bundleOf
import com.picnat.core.base.BaseFeatureVM
import com.picnat.feature_auth.R
import com.picnat.feature_auth.data.model.SignUpInfoModel
import com.picnat.feature_auth.feature.AuthFeature
import com.picnat.feature_auth.ui.sign_up_info.SignUpInfoFragment

class SignUpViewModel : BaseFeatureVM() {

    fun signUp(email: String, password: String, confirmPassword: String) {
        if (email.isBlank() || password.isBlank() || confirmPassword.isBlank()) {
            errorMessage.postValue(resourceProvider.getString(R.string.fill_all_the_fields))
            return
        }

        if (password != confirmPassword) {
            errorMessage.postValue(resourceProvider.getString(R.string.passwords_doesnt_match))
            return
        }

        val signUpInfoData = bundleOf(SignUpInfoFragment.SIGN_UP_INFO_MODEL to SignUpInfoModel(email, password))

        localNavigator.navigateTo(AuthFeature.AuthScreens.authSignUpInfo(signUpInfoData))
    }
}
