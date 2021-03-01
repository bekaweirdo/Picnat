package com.picnat.feature_auth.utils.ext

import android.text.TextUtils

fun String.isEmailValid(): Boolean {
    return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
}