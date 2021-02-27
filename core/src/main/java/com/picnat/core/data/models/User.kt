package com.picnat.core.data.models

data class User(
    val userId: Int,
    var userName: String,
    var userLastName : String,
    var userEmail: String,
    var userProfilePicture : String?)