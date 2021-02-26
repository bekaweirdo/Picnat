package com.picnat.core.data.models

import com.google.firebase.firestore.PropertyName

data class User(
    @PropertyName("userId") val userId: String,
    @PropertyName("username") val username: String,
    @PropertyName("userFirstName") val userFirstName: String,
    @PropertyName("userLastName") val userLastName: String,
    @PropertyName("userEmail") val userEmail: String,
    @PropertyName("userProfilePicture") val userProfilePicture: String?
) {
    constructor() : this("", "", "", "", "", null)
}