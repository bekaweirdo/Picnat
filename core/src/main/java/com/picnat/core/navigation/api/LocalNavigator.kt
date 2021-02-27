package com.picnat.core.navigation.api

import android.os.Bundle
import com.github.terrakok.cicerone.androidx.FragmentScreen

interface LocalNavigator {
    fun navigateTo(screen: FragmentScreen)
    fun clearFlowAndNavigateTo(screen: FragmentScreen)
}