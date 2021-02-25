package com.picnat.feature_auth.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SignUpInfoModel(
    val email : String,
    val password : String
) : Parcelable