package com.picnat.core.navigation.impl

import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.picnat.core.navigation.api.LocalNavigator

class LocalNavigatorImpl(private val router: Router) : LocalNavigator {

    override fun navigateWithoutClearing(screen: FragmentScreen) {
        router.navigateTo(screen, false)
    }

    override fun navigateTo(screen: FragmentScreen) {
        router.navigateTo(screen)
    }

    override fun replaceScreen(screen: FragmentScreen) {
        router.replaceScreen(screen)
    }
}