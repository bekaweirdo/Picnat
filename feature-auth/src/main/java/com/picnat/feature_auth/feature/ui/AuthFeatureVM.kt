package com.picnat.feature_auth.feature.ui

import com.picnat.core.base.BaseFeatureVM
import com.picnat.feature_auth.R
import com.picnat.feature_auth.utils.ext.isEmailValid

abstract class AuthFeatureVM : BaseFeatureVM() {

    protected fun checkFields(fields: List<String>): Boolean {
        fields.forEach {
            if (it.isBlank()) {
                errorMessage.postValue(resourceProvider.getString(R.string.fill_all_the_fields))
                return false
            }
        }
        return true
    }

    protected fun checkEmailValidity(email: String): Boolean {
        if (!email.isEmailValid()) {
            errorMessage.postValue(resourceProvider.getString(R.string.auth_email_isnt_valid))
            return false
        }
        return true
    }

    protected fun checkPasswordValidity(password: String): Boolean {
        if (password.length < 6 || password.length > 32) {
            errorMessage.postValue(resourceProvider.getString(R.string.auth_password_length_check))
            return false
        }
        return true
    }

}