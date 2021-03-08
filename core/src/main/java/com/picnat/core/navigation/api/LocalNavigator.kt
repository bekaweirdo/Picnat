package com.picnat.core.navigation.api

import android.os.Bundle
import com.github.terrakok.cicerone.androidx.FragmentScreen

interface LocalNavigator {
    fun navigateWithoutClearing(screen: FragmentScreen)
    fun navigateTo(screen: FragmentScreen)
    fun replaceScreen(screen: FragmentScreen)
}