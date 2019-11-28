package com.example.picnat.utils

import android.content.Context
import android.content.Intent
import com.example.picnat.ui.home.MainActivity
import com.example.picnat.ui.login.LoginActivity

//Log in
fun Context.startHomeActivity() =
    Intent(this, MainActivity::class.java).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }
//Log out
fun Context.startLoginActivity() =
    Intent(this,LoginActivity::class.java).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }