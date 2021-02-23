package com.picnat.core.navigation

import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.FragmentScreen

class LocalNavigatorHandlerImpl(private val router: Router) : LocalNavigatorHandler {
    override fun replaceScreen(screen: FragmentScreen) {
        router.replaceScreen(screen)
    }
}