package com.picnat.core.navigation.impl

import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.picnat.core.navigation.api.LocalNavigator

class LocalNavigatorImpl(private val router: Router) : LocalNavigator {

    override fun navigateTo(screen: FragmentScreen) {
        router.navigateTo(screen)
    }
    override fun clearFlowAndNavigateTo(screen: FragmentScreen) {
        router.navigateTo(screen, true)
    }
}