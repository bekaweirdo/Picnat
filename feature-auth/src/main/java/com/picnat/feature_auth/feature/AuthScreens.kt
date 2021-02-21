package com.picnat.feature_auth.feature

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.picnat.feature_auth.ui.login.LogInFragment
import com.picnat.feature_auth.ui.sign_up.SignUpFragment

object AuthScreens{
    fun LogIn() = FragmentScreen { LogInFragment() }
    fun SignUp() = FragmentScreen { SignUpFragment() }
}