package com.picnat.core.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen

interface LocalNavigatorHandler {
    fun replaceScreen(screen: FragmentScreen)
}